import json

import pandas as pd
import flask
from TreePolicy import *
import pickle
import time

top_N = 10
print('********Start at********')
print(time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
print('************************')
model_name = 'LSTM'
matrix_name = 'svdpp'
data = pd.read_csv('ratings.csv', header=0, names=['u_id', 'i_id', 'rating', 'timestep'])
# data = pd.read_table('ratings.dat', sep='::', names=['u_id', 'i_id', 'rating', 'timestep'])
user_idx = data['u_id'].unique()  # id for all the user

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
item_matrix = pickle.load(open('feature_matrix_' + matrix_name, mode='rb'))

def get_feature(input_id):
    # 根据输入的movie_id得出相应的feature
    movie_index = np.where(movie_id == input_id)
    return item_matrix[movie_index]

max_seq_length = 32
state_dim = item_matrix.shape[1] + 1
hidden_size = 64

agent = TreePolicy(state_dim=state_dim, layer=3, branch=16, learning_rate=1e-4, max_seq_length=max_seq_length)

print("Model loading...")
agent.load_model('./ckpt/svdpp_ckpt/tpgr_LSTM.ckpt')
print('Standing By')
session = agent.get_sess()

def normalize(rating):
    max_rating = 5
    min_rating = 0
    return -1 + 2 * (rating - min_rating) / (max_rating - min_rating)
    
def predict_TopN(predict_id):
    user_record = data[data['u_id'] == predict_id]
    all_state = []
    all_recommend = []
    count = 0
    for _, row in user_record.iterrows():
        movie_feature = get_feature(row['i_id'])
        current_state = np.hstack((movie_feature.flatten(), row['rating']))
        all_state.append(current_state)
        if len(all_state) > 1:
            temp_state = all_state[:-1]
            temp_state_length = len(temp_state)
            temp_state = agent.state_padding(temp_state, temp_state_length)
            output_action = agent.get_action_prob(temp_state, temp_state_length).flatten()
            output_action = output_action[:len(movie_id)]
            recommend_idx = np.argsort(-output_action)[:100]
            all_recommend.append(recommend_idx)
    print('recommend_idx[:',top_N,']:',recommend_idx[:top_N])
    return recommend_idx[:top_N].tolist()

# 实例化 flask
app = flask.Flask(__name__)

@app.route('/predict',methods=['GET'])
def predict():
    print()
    data = {"success": False}

    params = flask.request.json
    if (params == None):
        params = flask.request.args

    # 若发现参数，则返回预测值
    if (params != None):
        userid = int(params.get('userId'))
        with session.as_default():
            data["prediction"] = predict_TopN(userid)
        data["success"] = True

    # 返回Json格式的响应
    res = flask.make_response(flask.jsonify(json.dumps(data)))
    res.headers['Access-Control-Allow-Origin'] = '*'
    return res

if __name__ == '__main__':
    app.config['JSON_AS_ASCII'] = False
    app.run(port=5000)

'''
def evaluate(recommend_id, item_id, rating, top_N):
    ''
    evalute the recommend result for each user.
    :param recommend_id: the recommend_result for each item, a list that contains the results for each item.
    :param item_id: item id.
    :param rating: user's rating on item.
    :param top_N: N, a real number of N for evaluation.
    :return: reward@N,precision@N,recall@N, MRR@N
    ''
    session_length = len(recommend_id)
    relevant = 0
    recommend_relevant = 0
    selected = 0
    output_reward = 0
    mrr = 0
    for ti in range(session_length):
        current_recommend_id = list(recommend_id[ti])[:top_N]
        current_item = item_id[ti]
        current_rating = rating[ti]
        if current_rating > 3.5:
            relevant += 1
            if current_item in current_recommend_id:
                recommend_relevant += 1
        if current_item in current_recommend_id:
            selected += 1
            output_reward += normalize(current_rating)
            rank = current_recommend_id.index(current_item)
            mrr += 1.0 / (rank + 1)
    recall = recommend_relevant / relevant if relevant is not 0 else 0
    precision = recommend_relevant / selected if selected is not 0 else 0
    return output_reward / session_length, precision, recall, mrr / session_length
'''

