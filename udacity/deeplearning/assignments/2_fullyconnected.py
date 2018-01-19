# ref: https://github.com/tensorflow/tensorflow/blob/master/tensorflow/examples/udacity/2_fullyconnected.ipynb

# These are all the modules we'll be using later. Make sure you can import them
# before proceeding further.
from __future__ import print_function

import math
import os
import random

import matplotlib.pyplot as plt
import numpy as np
import tensorflow as tf
from six.moves import cPickle as pickle
from six.moves import range

# global variables
data_root = 'data'  # Change me to store data elsewhere

train_dataset = None
train_labels = None
valid_dataset = None
valid_labels = None
test_dataset = None
test_labels = None
image_size = 28
num_labels = 10


def load_datasets():
    global train_dataset, train_labels, valid_dataset, valid_labels, test_dataset, test_labels
    with open(os.path.join(data_root, 'notMNIST.pickle'), 'rb') as f:
        save = pickle.load(f)
        train_dataset = save['train_dataset']
        train_labels = save['train_labels']
        valid_dataset = save['valid_dataset']
        valid_labels = save['valid_labels']
        test_dataset = save['test_dataset']
        test_labels = save['test_labels']
        del save  # hint to help gc free up memory
    # reformat datasets and labels for tensorflow
    train_dataset, train_labels = reformat(train_dataset, train_labels)
    valid_dataset, valid_labels = reformat(valid_dataset, valid_labels)
    test_dataset, test_labels = reformat(test_dataset, test_labels)
    print('Training set', train_dataset.shape, train_labels.shape)
    print('Validation set', valid_dataset.shape, valid_labels.shape)
    print('Test set', test_dataset.shape, test_labels.shape)


def reformat(dataset, labels):
    dataset = dataset.reshape((-1, image_size * image_size)).astype(np.float32)
    # Map 0 to [1.0, 0.0, 0.0 ...], 1 to [0.0, 1.0, 0.0 ...]
    labels = (np.arange(num_labels) == labels[:, None]).astype(np.float32)
    return dataset, labels


def accuracy(predictions, labels):
    return (100.0 * np.sum(np.argmax(predictions, 1) == np.argmax(labels, 1)) / predictions.shape[0])


def display_sample_prediction(predictions, labels, accuracy, sample_size=10, columns=2):
    predictions = np.argmax(predictions, 1)
    labels = np.argmax(labels, 1)
    sample_index = [i for i in random.sample(range(len(labels)), sample_size)]

    fig = plt.figure(figsize=(image_size, image_size))
    plt.gcf().canvas.set_window_title('Deep Learning Assignment 2 @ Udacity')
    fig.suptitle('prediction v.s. label (%.1f%%)' % accuracy)
    fig.subplots_adjust(hspace=0.5)
    rows = math.ceil(sample_size / columns)
    img_pos = 1
    for index in sample_index:
        subplot = fig.add_subplot(rows, columns, img_pos)
        subplot.set_title('Label %s v.s. Predict %s' % (chr(ord('A') + labels[index]),
                                                        chr(ord('A') + predictions[index])))
        plt.imshow(test_dataset[index].reshape(image_size, image_size))
        img_pos += 1
    plt.show()


def tf_logistic_regression_with_simple_gradient_descent(epochs=801, train_subset=10000):
    log_folder = os.path.join(data_root, 'assignment2', 'lr1')
    graph = tf.Graph()
    with graph.as_default():
        # Input data.
        # Load the training, validation and test data into constants that are
        # attached to the graph.
        tf_train_dataset = tf.constant(train_dataset[:train_subset, :])  # [10000, 784]
        tf_train_labels = tf.constant(train_labels[:train_subset])  # [10000, 10]
        tf_valid_dataset = tf.constant(valid_dataset)
        tf_test_dataset = tf.constant(test_dataset)

        # Variables.
        # These are the parameters that we are going to be training. The weight
        # matrix will be initialized using random values following a (truncated)
        # normal distribution. The biases get initialized to zero.
        weights = tf.Variable(tf.truncated_normal([image_size * image_size, num_labels]))  # weights: [784, 10]
        biases = tf.Variable(tf.zeros([num_labels]))  # biases: [10]

        # Training computation.
        # We multiply the inputs with the weight matrix, and add biases. We compute
        # the softmax and cross-entropy (it's one operation in TensorFlow, because
        # it's very common, and it can be optimized). We take the average of this
        # cross-entropy across all training examples: that's our loss.
        logits = tf.matmul(tf_train_dataset, weights) + biases  # [10000, 784] * [784, 10] + [10] = [1, 10]
        loss = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(labels=tf_train_labels, logits=logits))

        # Optimizer.
        # We are going to find the minimum of this loss using gradient descent.
        optimizer = tf.train.GradientDescentOptimizer(0.5).minimize(loss)

        # Predictions for the training, validation, and test data.
        # These are not part of training, but merely here so that we can report
        # accuracy figures as we train.
        train_prediction = tf.nn.softmax(logits)
        valid_prediction = tf.nn.softmax(tf.matmul(tf_valid_dataset, weights) + biases)
        test_prediction = tf.nn.softmax(tf.matmul(tf_test_dataset, weights) + biases)

    with tf.Session(graph=graph) as session:
        # This is a one-time operation which ensures the parameters get initialized as
        # we described in the graph: random weights for the matrix, zeros for the
        # biases.
        tf.global_variables_initializer().run()
        print('Initialized')
        tf.summary.FileWriter(log_folder, session.graph)
        for step in range(epochs):
            # Run the computations. We tell .run() that we want to run the optimizer,
            # and get the loss value and the training predictions returned as numpy
            # arrays.
            _, l, predictions = session.run([optimizer, loss, train_prediction])
            if step % 100 == 0:
                print('Loss at step %d: %f' % (step, l))
                print('Training accuracy: %.1f%%' % accuracy(predictions, train_labels[:train_subset, :]))
                # Calling .eval() on valid_prediction is basically like calling run(), but
                # just to get that one numpy array. Note that it recomputes all its graph
                # dependencies.
                print('\tValidation accuracy: %.1f%%' % accuracy(valid_prediction.eval(), valid_labels))
        test_prediction_eval = test_prediction.eval()
        acc = accuracy(test_prediction_eval, test_labels)
        print("Test accuracy: %.1f%%" % acc)
        display_sample_prediction(test_prediction_eval, test_labels, acc)


def tf_logistic_regression_with_stochastic_gradient_descent(epochs=3001, batch_size=128):
    log_folder = os.path.join(data_root, 'assignment2', 'lr2')
    graph = tf.Graph()
    with graph.as_default():
        # Input data. For the training data, we use a placeholder that will be fed
        # at run time with a training minibatch.
        tf_train_dataset = tf.placeholder(tf.float32, shape=(batch_size, image_size * image_size), name='train_data')
        tf_train_labels = tf.placeholder(tf.float32, shape=(batch_size, num_labels), name='train_labels')
        tf_valid_dataset = tf.constant(valid_dataset, name='valid_data')
        tf_test_dataset = tf.constant(test_dataset, name='test_data')

        # Variables.
        weights = tf.Variable(tf.truncated_normal([image_size * image_size, num_labels]), name='weights')
        biases = tf.Variable(tf.zeros([num_labels]), name='biases')

        # Training computation.
        logits = tf.add(tf.matmul(tf_train_dataset, weights), biases, name='logits')
        loss = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(labels=tf_train_labels, logits=logits, name='sm'))

        # Optimizer.
        optimizer = tf.train.GradientDescentOptimizer(0.5).minimize(loss)

        # Predictions for the training, validation, and test data.
        train_prediction = tf.nn.softmax(logits)
        valid_prediction = tf.nn.softmax(tf.matmul(tf_valid_dataset, weights) + biases)
        test_prediction = tf.nn.softmax(tf.matmul(tf_test_dataset, weights) + biases)

    with tf.Session(graph=graph) as session:
        tf.global_variables_initializer().run()
        print("Initialized")
        tf.summary.FileWriter(log_folder, session.graph)
        for step in range(epochs):
            # Pick an offset within the training data, which has been randomized.
            # Note: we could use better randomization across epochs.
            offset = (step * batch_size) % (train_labels.shape[0] - batch_size)
            # Generate a minibatch.
            batch_data = train_dataset[offset:(offset + batch_size), :]
            batch_labels = train_labels[offset:(offset + batch_size), :]
            # Prepare a dictionary telling the session where to feed the minibatch.
            # The key of the dictionary is the placeholder node of the graph to be fed,
            # and the value is the numpy array to feed to it.
            feed_dict = {tf_train_dataset: batch_data, tf_train_labels: batch_labels}
            _, l, predictions = session.run([optimizer, loss, train_prediction], feed_dict=feed_dict)
            if step % 500 == 0:
                print("Minibatch loss at step %d: %f" % (step, l))
                print("Minibatch accuracy: %.1f%%" % accuracy(predictions, batch_labels))
                print("\tValidation accuracy: %.1f%%" % accuracy(valid_prediction.eval(), valid_labels))
        test_prediction_eval = test_prediction.eval()
        acc = accuracy(test_prediction_eval, test_labels)
        print("Test accuracy: %.1f%%" % acc)
        display_sample_prediction(test_prediction_eval, test_labels, acc)


def run_problem_1_with_leru(epochs=3001, batch_size=128, hidden_nodes=1024):
    log_folder = os.path.join(data_root, 'assignment2', 'lr3')
    graph = tf.Graph()
    with graph.as_default():
        # Input data. For the training data, we use a placeholder that will be fed
        # at run time with a training minibatch.
        tf_train_dataset = tf.placeholder(tf.float32, shape=(batch_size, image_size * image_size), name='train_data')
        tf_train_labels = tf.placeholder(tf.float32, shape=(batch_size, num_labels), name='train_label')
        tf_valid_dataset = tf.constant(valid_dataset, name='valid_data')
        tf_test_dataset = tf.constant(test_dataset, name='test_data')

        # Variables.
        weights_0 = tf.Variable(tf.truncated_normal([image_size * image_size, hidden_nodes]), name='input_weights')
        biases_0 = tf.Variable(tf.zeros([hidden_nodes]), name='input_biases')

        weights_1 = tf.Variable(tf.truncated_normal([hidden_nodes, num_labels]), name='relu_weights')
        biases_1 = tf.Variable(tf.zeros([num_labels]), name='relu_biases')

        # Training computation.
        logits_0 = tf.add(tf.matmul(tf_train_dataset, weights_0), biases_0, name='logits_0')
        relu = tf.nn.relu(logits_0, name='relu')
        logits_1 = tf.add(tf.matmul(relu, weights_1), biases_1, name='logits_1')
        loss = tf.reduce_mean(
            tf.nn.softmax_cross_entropy_with_logits(labels=tf_train_labels, logits=logits_1, name='lsm'))

        # Optimizer.
        optimizer = tf.train.GradientDescentOptimizer(0.5).minimize(loss)

        # Predictions for the training, validation, and test data.
        train_prediction = tf.nn.softmax(logits_1)
        valid_prediction = tf.nn.softmax(
            tf.matmul(tf.nn.relu(tf.matmul(valid_dataset, weights_0) + biases_0), weights_1) + biases_1)
        test_prediction = tf.nn.softmax(
            tf.matmul(tf.nn.relu(tf.matmul(tf_test_dataset, weights_0) + biases_0), weights_1) + biases_1)

    with tf.Session(graph=graph) as session:
        tf.global_variables_initializer().run()
        print("Initialized")
        tf.summary.FileWriter(log_folder, session.graph)
        for step in range(epochs):
            # Pick an offset within the training data, which has been randomized.
            # Note: we could use better randomization across epochs.
            offset = (step * batch_size) % (train_labels.shape[0] - batch_size)
            # Generate a minibatch.
            batch_data = train_dataset[offset:(offset + batch_size), :]
            batch_labels = train_labels[offset:(offset + batch_size), :]
            # Prepare a dictionary telling the session where to feed the minibatch.
            # The key of the dictionary is the placeholder node of the graph to be fed,
            # and the value is the numpy array to feed to it.
            feed_dict = {tf_train_dataset: batch_data, tf_train_labels: batch_labels}
            _, l, predictions = session.run([optimizer, loss, train_prediction], feed_dict=feed_dict)
            if step % 500 == 0:
                print("Minibatch loss at step %d: %f" % (step, l))
                print("Minibatch accuracy: %.1f%%" % accuracy(predictions, batch_labels))
                print("\tValidation accuracy: %.1f%%" % accuracy(valid_prediction.eval(), valid_labels))
        test_prediction_eval = test_prediction.eval()
        acc = accuracy(test_prediction_eval, test_labels)
        print("Test accuracy: %.1f%%" % acc)
        display_sample_prediction(test_prediction_eval, test_labels, acc)


from keras.models import Sequential
from keras.layers import Dense
from keras.losses import categorical_crossentropy
from keras.optimizers import SGD
from keras.callbacks import TensorBoard


def run_problem_1_with_keras(epochs=9, batch_size=128, hidden_nodes=1024):
    log_folder = os.path.join(data_root, 'assignment2', 'keras')
    model = Sequential()
    model.add(Dense(units=hidden_nodes, activation='relu', input_dim=image_size * image_size))
    model.add(Dense(units=num_labels, activation='softmax'))
    model.compile(loss=categorical_crossentropy, metrics=['accuracy'],
                  optimizer=SGD(lr=0.01, decay=1e-6, momentum=0.9, nesterov=True))
    model.fit(train_dataset, train_labels, epochs=epochs, batch_size=batch_size, verbose=False,
              callbacks=[TensorBoard(log_folder)])
    print("Validate accuracy: %.1f%%" % (model.evaluate(valid_dataset, valid_labels, verbose=False)[1] * 100))
    acc = 100 * model.evaluate(test_dataset, test_labels, verbose=False)[1]
    print("Test accuracy: %.1f%%" % acc)
    predictions = model.predict(test_dataset, verbose=False)
    display_sample_prediction(predictions, test_labels, acc)
    pass


if __name__ == '__main__':
    load_datasets()

    tf_logistic_regression_with_simple_gradient_descent()
    tf_logistic_regression_with_stochastic_gradient_descent()
    run_problem_1_with_leru()
    run_problem_1_with_keras()
