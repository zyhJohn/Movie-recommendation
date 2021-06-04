import tensorflow as tf
import numpy as np
import tensorflow.contrib.slim as slim
import os


class TreePolicy:
    def __init__(self, state_dim, layer=3, branch=32, hidden_size=64, learning_rate=1e-3, seed=1, max_seq_length=32,
                 stddev=0.03):
        self.state_dim = state_dim
        self.layer = layer
        self.branch = branch
        self.hidden_size = hidden_size
        self.learning_rate = learning_rate
        self.seed = seed
        self.max_seq_length = max_seq_length
        self.stddev = stddev
        np.random.seed(self.seed)
        tf.set_random_seed(self.seed)
        self.input_state = tf.placeholder(dtype=tf.float32, shape=[None, self.max_seq_length, state_dim])
        self.input_state_length = tf.placeholder(dtype=tf.int32, shape=[None, ])
        self.input_action = tf.placeholder(dtype=tf.int32, shape=[None, ])
        self.input_reward = tf.placeholder(dtype=tf.float32, shape=[None, ])
        self.tree = self.create_tree()
        self.output_action_prob = self.forward_pass()
        action_mask = tf.one_hot(self.input_action, self.branch ** self.layer)  #输出每个节点的动作
        prob_under_policy = tf.reduce_sum(self.output_action_prob * action_mask, axis=1)
        self.loss = -tf.reduce_mean(self.input_reward * tf.log(prob_under_policy + 1e-13), axis=0)
        self.train_step = tf.train.AdamOptimizer(self.learning_rate).minimize(self.loss)
        self.sess = tf.Session()
        self.sess.run(tf.global_variables_initializer())
        self.saver = tf.train.Saver()

    def feature_extract(self, input_state):
        #为推荐系统创建一个基于RNN的特征提取器
        with tf.variable_scope('feature_extract', reuse=False):
            basic_cell = tf.contrib.rnn.GRUCell(num_units=self.hidden_size)
            _, states = tf.nn.dynamic_rnn(basic_cell, input_state, dtype=tf.float32,
                                          sequence_length=self.input_state_length)
        return states

    def feature_extract_caser(self, input_state):
        with tf.variable_scope('feature_extract_caser', reuse=False):
            input_state = tf.expand_dims(input_state, axis=3)
            output_v = tf.layers.conv2d(input_state, self.hidden_size, [self.max_seq_length, 1],
                                        activation=tf.nn.relu)
            out_v = tf.layers.flatten(output_v)  # [N, self.state_dim*self.hidden_size]
            output_hs = list()
            for h in np.arange(self.max_seq_length) + 1:
                conv_out = tf.layers.conv2d(input_state, self.hidden_size, [h, self.state_dim],
                                            activation=tf.nn.relu)
                conv_out = tf.reshape(conv_out, [-1, self.max_seq_length - h + 1, self.hidden_size])
                pool_out = tf.layers.max_pooling1d(conv_out, [self.max_seq_length + 1 - h], 1)
                pool_out = tf.squeeze(pool_out, 1)
                output_hs.append(pool_out)
            out_h = tf.concat(output_hs, axis=1)
            out = tf.concat([out_v, out_h], axis=1)
            z = tf.layers.dense(out, self.hidden_size, activation=tf.nn.relu)
        return z

    def feature_extract_atem(self, input_state):
        #建立ATEM模型
        with tf.variable_scope('feature_extractor_atem', reuse=False):
            item_embedding = slim.fully_connected(input_state, self.hidden_size)  # [N, max_seq_length,hidden_size]
            attention_layer = slim.fully_connected(item_embedding, 1)
            attention_weight = tf.nn.softmax(attention_layer, 1)  # [N, max_seq_length, 1]
            output_layer = attention_weight * item_embedding  # [N, 1, hidden_size]
            output_layer = tf.reduce_sum(output_layer, axis=1)
        return output_layer

    def mlp(self, id=None):
        #建立一个多层神经网络作为树节点
        #输出维数等于branch的多层神经网络。
        with tf.variable_scope('node_' + str(id), reuse=False):
            state = self.feature_extract(self.input_state)
            l1 = slim.fully_connected(state, self.hidden_size)
            l2 = slim.fully_connected(l1, self.hidden_size)
            l3 = slim.fully_connected(l2, self.branch)
            outputs = tf.nn.softmax(l3)
        return outputs  # [N, branch]


    def create_tree(self):
        #构建平衡层次聚类树
        #total_nodes = int((self.branch ** self.layer - 1) / (self.branch - 1))
        layer_nodes = []
        for i in range(self.layer):
            current_layer = [self.mlp(id=str(i) + '_' + str(_)) for _ in range(int(self.branch ** i))]
            layer_nodes.append(current_layer)
        return layer_nodes

    def forward_pass(self):
        #计算每个项目的输出概率
        #返回一个树型tensor
        root_node = self.tree[0]
        root_output = root_node[0]
        for i in range(1, self.layer):  #遍历每一层平衡层次聚类树
            current_output = []
            for j in range(self.branch ** i):  #遍历每一个叶子节点
                current_layer = self.tree[i]
                current_output.append(tf.reshape(root_output[:, j], [-1, 1]) * current_layer[j])
            root_output = tf.concat(current_output, axis=1)  # 更新root_output,[N, branch**i]
        return root_output

    def get_action_prob(self, state, length):
        #获得每一个动作的概率
        state = np.reshape(state, [-1, self.max_seq_length, self.state_dim])
        length = np.reshape(length, [-1, ])
        return self.sess.run(self.output_action_prob,
                             feed_dict={self.input_state: state, self.input_state_length: length})

    def train(self, state, length, action, reward):
        #更新策略网络的梯度
        #返回每一次更新时的loss值
        state = np.reshape(state, [-1, self.max_seq_length, self.state_dim])
        length = np.reshape(length, [-1, ])
        action = np.reshape(action, [-1, ])
        reward = np.reshape(reward, [-1, ])
        loss = self.sess.run(self.loss, feed_dict={self.input_state: state, self.input_action: action,
                                                   self.input_reward: reward, self.input_state_length: length})
        self.sess.run(self.train_step, feed_dict={self.input_state: state, self.input_action: action,
                                                  self.input_reward: reward, self.input_state_length: length})
        return loss

    def state_padding(self, input_state, input_state_length):
        if input_state_length > self.max_seq_length:
            input_state = input_state[-self.max_seq_length:]
            input_state_length = self.max_seq_length
        input_state = np.array(input_state).reshape([input_state_length, self.state_dim])
        if input_state_length < self.max_seq_length:
            #填充零矩阵.
            padding_mat = np.zeros([self.max_seq_length - input_state_length, self.state_dim])
            input_state = np.vstack((input_state, padding_mat))
        return input_state

    def save_model(self,save_address):
        self.saver.save(self.sess, save_address)

    def load_model(self,load_address):
        self.saver.restore(self.sess, load_address)

    def get_sess(self):
        return self.sess