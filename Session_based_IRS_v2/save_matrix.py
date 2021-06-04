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
train_id = user_idx[:int(len(user_idx) * 0.8)]
test_id = user_idx[int(len(user_idx) * 0.8):]

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
rating_mat = np.zeros([len(train_id), len(movie_id)])
movie_id = np.array(movie_id)
for idx in train_id:  # 针对每个train数据
    record = data[data['u_id'] == idx]  # record有多个数据，所以row_index也有多个
    for _, row in record.iterrows():  # 针对每个用户的每条评分
        r = np.where(train_id == idx)
        c = np.where(row['i_id'] == movie_id)
        rating_mat[r, c] = row['rating']

# SVD for item representation
train = data[data['u_id'].isin(train_id)]
test = data[data['u_id'].isin(test_id)]
# svd = SVD(learning_rate=1e-3, regularization=0.005, n_epochs=200, n_factors=128, min_rating=0, max_rating=5)
# svd.fit(X=data, X_val=test, early_stopping=True, shuffle=False)
print("Begin Matrix Factorization")
reader = Reader(rating_scale=(1, 5))
trainset = Dataset.load_from_df(data[['u_id', 'i_id', 'rating']], reader)
trainset = trainset.build_full_trainset()
svd = SVDpp()
svd.fit(trainset)
item_matrix = svd.qi

pickle.dump(item_matrix, open('feature_matrix_' + matrix_name, mode='wb'))

print(item_matrix)
print(item_matrix.shape)


