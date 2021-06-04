import pickle
import tensorflow as tf
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

model_name = 'LSTM'
matrix_name = 'bias'

item_matrix= pickle.load(open('feature_matrix_'+matrix_name, mode='rb'))
print(item_matrix)
print(item_matrix.shape)
state_dim = item_matrix.shape[1] + 1
print(state_dim)
# print('Result:')
# display = np.mean(np.array(result).reshape([-1, 8]), axis=0)
# for num in display:
#     print('%.5f' % num)

# reward = []
# precision = []
# recall = []
# for num in result:
#     reward.append(num[0])
#     precision.append(num[1])
#     recall.append(num[2])
#
# plt.plot(reward)
# plt.plot(precision)
# plt.plot(recall)

# plt.plot(result)
# plt.show()