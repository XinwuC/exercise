import queue


# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

    @staticmethod
    def construct_tree(nodes=[]):
        '''
        construct a tree from a list of nodes

        :param nodes: list of nodes
        :return: root node
        '''
        if not nodes:
            return None

        root = TreeNode(nodes[0])
        q = queue.Queue()
        q.put(root)
        index = 1
        length = len(nodes)
        while q.not_empty:
            current = q.get()
            if index < length:
                if nodes[index] is not None:
                    current.left = TreeNode(nodes[index])
                    q.put(current.left)
                index += 1
                if index < length:
                    if nodes[index] is not None:
                        current.right = TreeNode(nodes[index])
                        q.put(current.right)
                    index += 1
                else:
                    break
            else:
                break

        return root
