# ref: https://github.com/tensorflow/tensorflow/blob/master/tensorflow/examples/udacity/1_notmnist.ipynb

# These are all the modules we'll be using later. Make sure you can import them
# before proceeding further.
from __future__ import print_function
import imageio
import matplotlib.pyplot as plt
import matplotlib.image as mpimg
import numpy as np
import random
import os
import sys
import tarfile
import math
from sklearn.linear_model import LogisticRegression
from six.moves.urllib.request import urlretrieve
from six.moves import cPickle as pickle

# global variables
url = 'https://commondatastorage.googleapis.com/books1000/'
last_percent_reported = None
data_root = 'data'  # Change me to store data elsewhere
num_classes = 10
np.random.seed(133)
image_size = 28  # Pixel width and height.
pixel_depth = 255.0  # Number of levels per pixel.
train_folders = None
test_folders = None
train_dataset = None
train_labels = None
test_dataset = None
test_labels = None
valid_dataset = None
valid_labels = None


###############################################################
# solve problems 1 - 6
###############################################################
def run_problem_1():
    print('[1] Display training/validation/test images from files.')
    # display training images
    _run_problem_1_display(train_folders, 'Training image samples from: %s' % os.path.dirname(train_folders[0]))
    # display test images
    _run_problem_1_display(test_folders, 'Test image samples from: %s' % os.path.dirname(test_folders[0]))


def _run_problem_1_display(data_folders, title, columns=2):
    fig = plt.figure(figsize=(image_size, image_size))
    plt.gcf().canvas.set_window_title('Deep Learning Assignment 1 problem 1 @ Udacity')
    fig.suptitle(title)
    fig.subplots_adjust(hspace=0.5)

    rows = math.ceil(len(data_folders) / columns)
    index = 1
    for data_folder in data_folders:
        img_file = os.path.join(data_folder, random.sample(os.listdir(data_folder), 1)[0])
        img = mpimg.imread(img_file)
        subplot = fig.add_subplot(rows, columns, index)
        subplot.set_title(img_file)
        plt.imshow(img)
        index += 1
    plt.show()


def run_problem_2():
    print('[2] Display training/validation/test images and labels from ndarray.')
    _run_problem_2_display(train_dataset, train_labels, 'Training data set & Labels')
    _run_problem_2_display(valid_dataset, valid_labels, 'Validation data set & Labels')
    _run_problem_2_display(test_dataset, test_labels, 'Test data set & Labels')


def _run_problem_2_display(dataset, labels, title, sample_size=10, columns=2):
    fig = plt.figure(figsize=(image_size, image_size))
    plt.gcf().canvas.set_window_title('Deep Learning Assignment 1 problem 2 @ Udacity')
    fig.suptitle(title)
    fig.subplots_adjust(hspace=0.5)

    sample_index = random.sample(range(len(labels)), sample_size)
    rows = math.ceil(sample_size / columns)
    img_pos = 1
    for index in sample_index:
        subplot = fig.add_subplot(rows, columns, img_pos)
        subplot.set_title(chr(ord('A') + labels[index]))
        plt.imshow(dataset[index])
        img_pos += 1
    plt.show()


def run_problem_3():
    """
    Another check: we expect the data to be balanced across classes. Verify that.

    :return: na
    """
    print('[3] Validate data balance across classes.')
    full_dataset = np.concatenate((train_dataset, valid_dataset, test_dataset))
    print('Full data set tensor: ', full_dataset.shape)
    print('Mean: ', np.mean(full_dataset))
    print('Standard deviation: ', np.std(full_dataset))


def run_problem_4():
    """
    Convince yourself that the data is still good after shuffling!

    :return: na
    """
    print('[4] Validate training/validation/test data balance after shuffle.')
    _run_problem_4_validate(train_dataset, 'training')
    _run_problem_4_validate(valid_dataset, 'validation')
    _run_problem_4_validate(test_dataset, 'test')


def _run_problem_4_validate(dataset, dataset_name):
    print('%s data set tensor: ' % dataset_name, dataset.shape)
    print('\tMean: ', np.mean(dataset))
    print('\tStandard deviation: ', np.std(dataset))


def run_problem_5():
    """
    Measure how much overlap there is between training, validation and test samples.

    :return: na
    """
    print('[5] Measure how much overlap there is between training, validation and test samples.')
    # check dups within datasets
    dups = _run_problem_5_dups_self(test_dataset, test_labels)
    print('\t{0:d} ({1:.2%}) dups within test data sets.'.format(dups, dups / len(test_labels)))
    dups = _run_problem_5_dups_self(valid_dataset, valid_labels)
    print('\t{0:d} ({1:.2%}) dups within test data sets.'.format(dups, dups / len(valid_labels)))
    dups = _run_problem_5_dups_self(train_dataset, train_labels)
    print('\t{0:d} ({1:.2%}) dups within test data sets.'.format(dups, dups / len(train_labels)))
    # check dups between datasets
    dups = _run_problem_5_dups_between(test_dataset, test_labels, valid_dataset, valid_labels)
    print('\t{0:d} ({1:.2%}) dups between test and validate data sets.'.format(dups, dups / len(test_labels)))
    dups = _run_problem_5_dups_between(test_dataset, test_labels, train_dataset, train_labels)
    print('\t{0:d} ({1:.2%}) dups between test and training data sets.'.format(dups, dups / len(test_labels)))
    dups = _run_problem_5_dups_between(valid_dataset, valid_labels, train_dataset, train_labels)
    print('\t{0:d} ({1:.2%}) dups between validate and training data sets.'.format(dups, dups / len(valid_dataset)))


def _run_problem_5_dups_self(dataset, labels):
    dup_count = 0
    for labels, index in _build_label_index(labels).items():
        while index:
            dups = [index[0]]
            for i in range(1, len(index)):
                if np.array_equal(dataset[index[0]], dataset[index[i]]):
                    dups.append(index[i])
            if len(dups) > 1:
                dup_count += 1
            for dup in dups:
                index.remove(dup)
    return dup_count


def _run_problem_5_dups_between(left_dataset, left_labels, right_dataset, right_labels):
    """

    :param left_dataset: smaller dataset
    :param left_labels:
    :param right_dataset: larger dataset
    :param right_labels:
    :return:
    """
    left_index = _build_label_index(left_labels)
    right_index = _build_label_index(right_labels)
    dup_count = 0
    for label, left_imgs in left_index.items():
        right_imgs = right_index[label]
        for left_img in left_imgs:
            for right_img in right_imgs:
                if np.array_equal(left_dataset[left_img], right_dataset[right_img]):
                    dup_count += 1
                    break
    return dup_count


def _build_label_index(labels):
    label_index = {}
    for index in range(len(labels)):
        if labels[index] not in label_index.keys():
            label_index[labels[index]] = [index]
        else:
            label_index[labels[index]].append(index)
    return label_index


def run_problem_6(force=False):
    """
    Use the LogisticRegression model from sklearn.linear_model.

    :return:
    """
    print('[6] Use Multinomial Logistic Regression to learn and predict.')

    model = None
    model_file = os.path.join(data_root, 'assignment_1_model.pickle')
    if force or not os.path.exists(model_file):
        train_set = train_dataset.reshape(train_dataset.shape[0], image_size * image_size)
        model = LogisticRegression(C=1e5, multi_class='multinomial', solver='newton-cg')
        model.fit(train_set, train_labels)
        try:
            with open(model_file, 'wb') as f:
                pickle.dump(model, f)
        except Exception as e:
            print('Unable to save model to', model_file, ':', e)
    else:
        try:
            with open(model_file, 'rb') as f:
                model = pickle.load(f)
        except Exception as e:
            print('Unable to load model from', model_file, ':', e)
            return

    test_set = test_dataset.reshape(test_dataset.shape[0], image_size * image_size)
    sparsity = np.mean(model.coef_ == 0) * 100
    score = model.score(test_set, test_labels)
    print("Sparsity with L1 penalty: %.2f%%" % sparsity)
    print("Test score with L1 penalty: %.4f" % score)
    _run_problem_6_display(model)


def _run_problem_6_analyze_model(model):
    coef = model.coef_.copy()
    plt.figure(figsize=(10, 5))
    scale = np.abs(coef).max()
    for i in range(10):
        l1_plot = plt.subplot(2, 5, i + 1)
        l1_plot.imshow(coef[i].reshape(28, 28), interpolation='nearest',
                       cmap=plt.cm.RdBu, vmin=-scale, vmax=scale)
        l1_plot.set_xticks(())
        l1_plot.set_yticks(())
        l1_plot.set_xlabel('Class %i' % i)
    plt.suptitle('Classification vector for...')
    plt.show()


def _run_problem_6_display(model, test_size=10):
    fig = plt.figure(figsize=(image_size, image_size))
    plt.gcf().canvas.set_window_title('Deep Learning Assignment 1 problem 6 @ Udacity')
    fig.suptitle('prediction v.s. label')
    fig.subplots_adjust(hspace=0.5)

    columns = 2
    rows = math.ceil(test_size / columns)
    img_pos = 1
    for index in random.sample(range(len(test_labels)), test_size):
        subplot = fig.add_subplot(rows, columns, img_pos)
        predict = model.predict([test_dataset[index].reshape(image_size * image_size)])[0]
        subplot.set_title('Label %s v.s. Predict %s' % (chr(ord('A') + test_labels[index]), chr(ord('A') + predict)))
        plt.imshow(test_dataset[index])
        img_pos += 1
    plt.show()


###############################################################
# main function and prepare data files
###############################################################
def prepare_data_files(force=False):
    global train_folders, test_folders
    global train_dataset, train_labels, test_dataset, test_labels, valid_dataset, valid_labels

    train_filename = maybe_download('notMNIST_large.tar.gz', 247336696, force)
    test_filename = maybe_download('notMNIST_small.tar.gz', 8458043, force)
    train_folders = maybe_extract(train_filename, force)
    test_folders = maybe_extract(test_filename, force)
    train_datasets = maybe_pickle(train_folders, 45000, force)
    test_datasets = maybe_pickle(test_folders, 1800, force)

    train_size = 200000
    valid_size = 10000
    test_size = 10000

    valid_dataset, valid_labels, train_dataset, train_labels = merge_datasets(
        train_datasets, train_size, valid_size)
    _, _, test_dataset, test_labels = merge_datasets(test_datasets, test_size)

    print('Training:', train_dataset.shape, train_labels.shape)
    print('Validation:', valid_dataset.shape, valid_labels.shape)
    print('Testing:', test_dataset.shape, test_labels.shape)

    train_dataset, train_labels = randomize(train_dataset, train_labels)
    test_dataset, test_labels = randomize(test_dataset, test_labels)
    valid_dataset, valid_labels = randomize(valid_dataset, valid_labels)

    pickle_file = os.path.join(data_root, 'notMNIST.pickle')

    try:
        f = open(pickle_file, 'wb')
        save = {
            'train_dataset': train_dataset,
            'train_labels': train_labels,
            'valid_dataset': valid_dataset,
            'valid_labels': valid_labels,
            'test_dataset': test_dataset,
            'test_labels': test_labels,
        }
        pickle.dump(save, f, pickle.HIGHEST_PROTOCOL)
        f.close()
    except Exception as e:
        print('Unable to save data to', pickle_file, ':', e)
        raise

    statinfo = os.stat(pickle_file)
    print('Compressed pickle size:', statinfo.st_size)


def download_progress_hook(count, blockSize, totalSize):
    """A hook to report the progress of a download. This is mostly intended for users with
    slow internet connections. Reports every 5% change in download progress.
    """
    global last_percent_reported
    percent = int(count * blockSize * 100 / totalSize)

    if last_percent_reported != percent:
        if percent % 5 == 0:
            sys.stdout.write("%s%%" % percent)
            sys.stdout.flush()
        else:
            sys.stdout.write(".")
            sys.stdout.flush()

        last_percent_reported = percent


def maybe_download(filename, expected_bytes, force=False):
    """Download a file if not present, and make sure it's the right size."""
    os.makedirs(data_root, exist_ok=True)
    dest_filename = os.path.join(data_root, filename)
    if force or not os.path.exists(dest_filename):
        print('Attempting to download:', filename)
        filename, _ = urlretrieve(url + filename, dest_filename, reporthook=download_progress_hook)
        print('\nDownload Complete!')
    statinfo = os.stat(dest_filename)
    if statinfo.st_size == expected_bytes:
        print('Found and verified', dest_filename)
    else:
        raise Exception(
            'Failed to verify ' + dest_filename + '. Can you get to it with a browser?')
    return dest_filename


def maybe_extract(filename, force=False):
    root = os.path.splitext(os.path.splitext(filename)[0])[0]  # remove .tar.gz
    if os.path.isdir(root) and not force:
        # You may override by setting force=True.
        print('%s already present - Skipping extraction of %s.' % (root, filename))
    else:
        print('Extracting data for %s. This may take a while. Please wait.' % root)
        tar = tarfile.open(filename)
        sys.stdout.flush()
        tar.extractall(data_root)
        tar.close()
    data_folders = [
        os.path.join(root, d) for d in sorted(os.listdir(root))
        if os.path.isdir(os.path.join(root, d))]
    if len(data_folders) != num_classes:
        raise Exception(
            'Expected %d folders, one per class. Found %d instead.' % (
                num_classes, len(data_folders)))
    print(data_folders)
    return data_folders


def load_letter(folder, min_num_images):
    """Load the data for a single letter label."""
    image_files = os.listdir(folder)
    dataset = np.ndarray(shape=(len(image_files), image_size, image_size),
                         dtype=np.float32)
    print(folder)
    num_images = 0
    for image in image_files:
        image_file = os.path.join(folder, image)
        try:
            image_data = (imageio.imread(image_file).astype(float) -
                          pixel_depth / 2) / pixel_depth
            if image_data.shape != (image_size, image_size):
                raise Exception('Unexpected image shape: %s' % str(image_data.shape))
            dataset[num_images, :, :] = image_data
            num_images = num_images + 1
        except (IOError, ValueError) as e:
            print('Could not read:', image_file, ':', e, '- it\'s ok, skipping.')

    dataset = dataset[0:num_images, :, :]
    if num_images < min_num_images:
        raise Exception('Many fewer images than expected: %d < %d' %
                        (num_images, min_num_images))

    print('Full dataset tensor:', dataset.shape)
    print('Mean:', np.mean(dataset))
    print('Standard deviation:', np.std(dataset))
    return dataset


def maybe_pickle(data_folders, min_num_images_per_class, force=False):
    dataset_names = []
    for folder in data_folders:
        set_filename = folder + '.pickle'
        dataset_names.append(set_filename)
        if os.path.exists(set_filename) and not force:
            # You may override by setting force=True.
            print('%s already present - Skipping pickling.' % set_filename)
        else:
            print('Pickling %s.' % set_filename)
            dataset = load_letter(folder, min_num_images_per_class)
            try:
                with open(set_filename, 'wb') as f:
                    pickle.dump(dataset, f, pickle.HIGHEST_PROTOCOL)
            except Exception as e:
                print('Unable to save data to', set_filename, ':', e)

    return dataset_names


def make_arrays(nb_rows, img_size):
    if nb_rows:
        dataset = np.ndarray((nb_rows, img_size, img_size), dtype=np.float32)
        labels = np.ndarray(nb_rows, dtype=np.int32)
    else:
        dataset, labels = None, None
    return dataset, labels


def merge_datasets(pickle_files, train_size, valid_size=0):
    num_classes = len(pickle_files)
    valid_dataset, valid_labels = make_arrays(valid_size, image_size)
    train_dataset, train_labels = make_arrays(train_size, image_size)
    vsize_per_class = valid_size // num_classes
    tsize_per_class = train_size // num_classes

    start_v, start_t = 0, 0
    end_v, end_t = vsize_per_class, tsize_per_class
    end_l = vsize_per_class + tsize_per_class
    for label, pickle_file in enumerate(pickle_files):
        try:
            with open(pickle_file, 'rb') as f:
                letter_set = pickle.load(f)
                # let's shuffle the letters to have random validation and training set
                np.random.shuffle(letter_set)
                if valid_dataset is not None:
                    valid_letter = letter_set[:vsize_per_class, :, :]
                    valid_dataset[start_v:end_v, :, :] = valid_letter
                    valid_labels[start_v:end_v] = label
                    start_v += vsize_per_class
                    end_v += vsize_per_class

                train_letter = letter_set[vsize_per_class:end_l, :, :]
                train_dataset[start_t:end_t, :, :] = train_letter
                train_labels[start_t:end_t] = label
                start_t += tsize_per_class
                end_t += tsize_per_class
        except Exception as e:
            print('Unable to process data from', pickle_file, ':', e)
            raise

    return valid_dataset, valid_labels, train_dataset, train_labels


def randomize(dataset, labels):
    permutation = np.random.permutation(labels.shape[0])
    shuffled_dataset = dataset[permutation, :, :]
    shuffled_labels = labels[permutation]
    return shuffled_dataset, shuffled_labels


if __name__ == '__main__':
    prepare_data_files()

    run_problem_1()
    run_problem_2()
    run_problem_3()
    run_problem_4()
    run_problem_5()
    run_problem_6()
