package medium;

import java.util.HashMap;

/**
 * 146. LRU Cache
 * https://leetcode.com/problems/lru-cache/description/
 * 
 * Design a data structure that follows the constraints of a Least Recently Used
 * (LRU) cache.
 * 
 * Implement the LRUCache class:
 * 
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise
 * return -1.
 * void put(int key, int value) Update the value of the key if the key exists.
 * Otherwise, add the key-value pair to the cache. If the number of keys exceeds
 * the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 * 
 * Example 1:
 * 
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * 
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1); // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2); // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1); // return -1 (not found)
 * lRUCache.get(3); // return 3
 * lRUCache.get(4); // return 4
 * 
 * 
 * Constraints:
 * 
 * 1 <= capacity <= 3000
 * 0 <= key <= 104
 * 0 <= value <= 105
 * At most 2 * 105 calls will be made to get and put.
 * 
 */

class m146_LRUCache {
    static class LRUCache {
        class Entry {
            int key;
            int value;
            Entry prev;
            Entry next;

            public Entry(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        int _capacity;
        HashMap<Integer, Entry> _map;
        Entry _head; // most recent accessed
        Entry _tail; // least recent accessed

        public LRUCache(int capacity) {
            _capacity = capacity;
            _map = new HashMap<>(capacity);
        }

        public int get(int key) {
            Entry e = _map.get(key);
            if (e != null) {
                access(e);
                return e.value;
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {
            Entry e = _map.get(key);
            if (e == null) {
                e = new Entry(key, value);
                if (_map.size() == _capacity) {
                    // remove least access entry
                    _map.remove(_tail.key);
                    if (_tail.prev != null) {
                        _tail.prev.next = null;
                        _tail = _tail.prev;
                    } else {
                        _head = null;
                        _tail = null;
                    }
                }
                _map.put(key, e);
            } else
                e.value = value;
            access(e);
        }

        private void access(Entry entry) {
            if (_head == entry)
                return;

            if (_head == null) {
                _head = entry;
                _tail = entry;
                return;
            }

            // 1. remove entry from the list
            if (entry.prev != null) {
                entry.prev.next = entry.next;
                if (entry.next != null)
                    entry.next.prev = entry.prev;
                else
                    _tail = entry.prev;
                entry.prev = null;
            }
            // 2. add back to head
            _head.prev = entry;
            entry.next = _head;
            _head = entry;

        }
    }

    private static void test(String[] ops, int[][] values, Integer[] expects) {
        LRUCache cache = new LRUCache(values[0][0]);
        for (int i = 1; i < ops.length; ++i) {
            switch (ops[i]) {
                case "put":
                    cache.put(values[i][0], values[i][1]);
                    break;
                case "get":
                    int ans = cache.get(values[i][0]);
                    System.out.format("%b: key %d; %d vs %d (expected)\n",
                            ans == expects[i], values[i][0], ans, expects[i]);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        test(new String[] { "LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get" },
                new int[][] { { 2 }, { 1, 1 }, { 2, 2 }, { 1 }, { 3, 3 }, { 2 }, { 4, 4 }, { 1 }, { 3 }, { 4 } },
                new Integer[] { null, null, null, 1, null, -1, null, -1, 3, 4 });
        test(new String[] { "LRUCache", "put", "put", "get", "put", "put", "get" },
                new int[][] { { 2 }, { 2, 1 }, { 2, 2 }, { 2 }, { 1, 1 }, { 4, 1 }, { 2 } },
                new Integer[] { null, null, null, 2, null, null, -1 });
    }
}