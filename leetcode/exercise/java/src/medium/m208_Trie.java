package medium;

/**
 * 208. Implement Trie (Prefix Tree)
 * <p>
 * Total Accepted: 59567
 * Total Submissions: 232739
 * Difficulty: Medium
 * Contributors: Admin
 * <p>
 * Implement a trie with insert, search, and startsWith methods.
 * <p>
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 * <p>
 * <p>
 * Leetcode: https://leetcode.com/problems/implement-trie-prefix-tree/
 * <p>
 * Created by xinwu on 1/2/17.
 */

class TrieNode {
    // Initialize your data structure here.
    boolean isWord = false;
    TrieNode[] children = new TrieNode[26];

    public TrieNode() {

    }
}

public class m208_Trie {
    private TrieNode root;

    public m208_Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (null == current.children[index]) current.children[index] = new TrieNode();
            current = current.children[index];
        }
        current.isWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (null == current.children[index]) return false;
            current = current.children[index];
        }

        return current.isWord;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (null == current.children[index]) return false;
            current = current.children[index];
        }

        return true;
    }

    public static void main(String[] args) {
        m208_Trie trie = new m208_Trie();
        trie.insert("somestring");
        trie.search("key");
    }
}


