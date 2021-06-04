import pandas as pd
import pickle
from TreePolicy import *
from surprise import SVDpp
from surprise import Dataset
from surprise import Reader

model_name = 'LSTM'
matrix_name = 'svdpp'
np.random.seed(1)
tf.set_random_seed(1)
data = pd.read_csv('ratings.csv', header=0, names=['u_id', 'i_id', 'rating', 'timestep'])
# data = pd.read_table('ratings.dat', sep='::', names=['u_id', 'i_id', 'rating', 'timestep'])
user_idx = data['u_id'].unique()  # id for all the user
np.random.shuffle(user_idx)

# Count the movies
movie_id = []
for idx1 in user_idx:  # 针对train_id中的每个用户
    user_record = data[data['u_id'] == idx1]
    for idx2, row in user_record.iterrows():
        if row['i_id'] in movie_id:
            idx = movie_id.index(row['i_id'])  # 找到该位置
        else:
            # 否则新加入movie_id
            movie_id.append(row['i_id'])

# Build user rating matrix
rating_mat = np.zeros([len(user_idx), len(movie_id)])
movie_id = np.array(movie_id)
for idx in user_idx:  # 针对每个train数据
    record = data[data['u_id'] == idx]  # record有多个数据，所以row_index也有多个
    for _, row in record.iterrows():  # 针对每个用户的每条评分
        r = np.where(user_idx == idx)
        c = np.where(row['i_id'] == movie_id)
        rating_mat[r, c] = row['rating']

# SVD for item representation
print("Begin Matrix Factorization")
reader = Reader(rating_scale=(1, 5))
trainset = Dataset.load_from_df(data[['u_id', 'i_id', 'rating']], reader)
trainset = trainset.build_full_trainset()
svd = SVDpp()
svd.fit(trainset)
item_matrix = svd.qi

pickle.dump(item_matrix, open('feature_matrix_' + matrix_name, mode='wb'))
print('Save feature matrix '+ matrix_name)

def get_feature(input_id):
    # 根据输入的movie_id得出相应的feature
    movie_index = np.where(movie_id == input_id)
    return item_matrix[movie_index]


def action_mapping(input_id):
    '''
    convert input movie id to index
    :param input_id: movie id
    :return: index of movie.
    '''
    return np.where(movie_id == input_id)


max_seq_length = 32
state_dim = item_matrix.shape[1] + 1
hidden_size = 64

agent = TreePolicy(state_dim=state_dim, layer=3, branch=16, learning_rate=1e-4, max_seq_length=max_seq_length)


def normalize(rating):
    max_rating = 5
    min_rating = 0
    return -1 + 2 * (rating - min_rating) / (max_rating - min_rating)


print('Begin training the tree policy.')
discount_factor = 1
train_step = 0
loss_list = []
for id1 in user_idx:
    user_record = data[data['u_id'] == id1]
    state = []
    rating = []
    action = []
    for _, row in user_record.iterrows():
        movie_feature = get_feature(row['i_id'])
        current_state = np.hstack((movie_feature.flatten(), row['rating']))
        rating.append(row['rating'])
        state.append(current_state)
        action.append(action_mapping(row['i_id']))
        if len(rating) % 32 is 0:
            state_list = []
            state_length_list = []
            action_list = []
            reward_list = []
            for i in range(2, len(state)):
                current_state = state[:i - 1]
                current_state_length = i - 1
                temp_state = agent.state_padding(current_state, current_state_length)
                state_length_list.append(current_state_length)
                state_list.append(temp_state)
                action_list.append(action[i])
                reward_list.append(normalize(rating[i]))  # normalization of the ratings to 0,1
            discount = discount_factor ** np.arange(len(reward_list))
            Q_value = np.cumsum(reward_list[::-1])
            Q_value = Q_value[::-1] * discount
            loss = agent.train(state_list, state_length_list, action_list, Q_value)
            loss_list.append(loss)
            train_step += 1
            print('Step ', train_step, 'Loss: ', loss)
            state = []
            rating = []
            action = []

agent.save_model()
print('Save model')