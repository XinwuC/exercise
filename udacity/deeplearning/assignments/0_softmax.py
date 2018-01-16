"""Softmax."""

scores = [3.0, 1.0, 0.2]

import numpy as np
import math
import matplotlib

def softmax(x):
    """Compute softmax values for each sets of scores in x."""
    x = np.array(x)
    result = []
    if x.ndim == 1:
        sum = 0
        for j in range(x.shape[0]):
            sum += math.pow(math.e, x[j])
        for i in range(x.shape[0]):
            result.append(math.pow(math.e, x[i]) / sum)
    elif x.ndim == 2:
        xt = x.T
        for sample in range(xt.shape[0]):
            sum = 0
            sample_result = []
            for j in range(xt.shape[1]):
                sum += math.pow(math.e, xt[sample][j])
            for i in range(xt.shape[1]):
                sample_result.append(math.pow(math.e, xt[sample][i]) / sum)
            result.append(sample_result)
    else:
        # not supported, return itself
        return x

    result = np.array(result).T
    return result


print(softmax(scores))

# Plot softmax curves
import matplotlib.pyplot as plt

x = np.arange(-2.0, 6.0, 0.1)
scores = np.vstack([x, np.ones_like(x), 0.2 * np.ones_like(x)])

plt.plot(x, softmax(scores).T, linewidth=2)
plt.show()