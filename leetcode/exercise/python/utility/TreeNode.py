import math
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

    @staticmethod
    def print_tree(root, val_length=2):
        '''
        print tree
        
        :param root: root node of the tree
        :return: void
        '''
        if root is None:
            print(root)
            return

        stack = [root]
        index = 0
        last_index = 0
        while index < len(stack):
            if stack[index] is not None:
                stack.append(stack[index].left)
                stack.append(stack[index].right)
                last_index = len(stack) - 1 if stack[index].right is not None \
                    else len(stack) - 2 if stack[index].left is not None \
                    else index
            elif index <= last_index:
                stack.append(None)
                stack.append(None)
            else:
                break
            index += 1
        height = math.floor(math.log2(len(stack) + 1)) - 1  # height start from 0

        space = ' ' * val_length
        for level in range(height + 1):
            start_index = int(math.pow(2, level)) - 1
            end_index = int(math.pow(2, level + 1)) - 2
            # print node level
            leading_space = ' ' * (height - level) * 2
            node_space = space * (height - level + 1)
            print(leading_space, end="")
            for index in range(start_index, end_index + 1):
                print('%2s%s' % (space if stack[index] is None else str(stack[index].val), node_space), end="")
            print()

            if level == height:
                break

            # print edge level
            leading_space = ' ' * ((height - level) * 2)
            edge_space = ' ' * (height - level)
            print(leading_space, end="")
            for index in range(start_index, end_index + 1):
                print(' ' if stack[index] is None or stack[index * 2 + 1] is None else '/', end="", flush=True)
                print(edge_space, end=" ", flush=True)
                print(' ' if stack[index] is None or stack[index * 2 + 2] is None else '\\', end="", flush=True)
                print(edge_space, end=" ", flush=True)
            print()


if __name__ == '__main__':
    root = TreeNode.construct_tree([1, 3, 2, 5, 3, None, 9, 10, None, 11, 12, None, 15])
    TreeNode.print_tree(root)
