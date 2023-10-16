package hard;

import java.util.HashMap;

/**
 * 460. LFU Cache
 * <p>
 * Total Accepted: 1650
 * Total Submissions: 9394
 * Difficulty: Hard
 * Contributors: 1337c0d3r, fishercoder
 * <p>
 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following
 * operations: get and set.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * <p>
 * set(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity,
 * it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem,
 * when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be
 * evicted.
 * <p>
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * <p>
 * Example:
 * <code>
 * LFUCache cache = new LFUCache( 2  );
 * cache.set(1, 1);
 * cache.set(2, 2);
 * cache.get(1);       // returns 1
 * cache.set(3, 3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.get(3);       // returns 3.
 * cache.set(4, 4);    // evicts key 1.
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 * </code>
 * <p>
 * Your LFUCache object will be instantiated and called as such:
 * <code>
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.set(key,value);
 * </code>
 * <p>
 * Leetcode: https://leetcode.com/problems/lfu-cache/
 * <p>
 * Created by xinwu on 12/10/16.
 */
public class h460_LFUCache {
    private HashMap<Integer, LFUCacheEntry> _hashmap;
    private int _capacity;
    private boolean _isFull;
    private LFUCacheEntry _head;

    class LFUCacheEntry {
        private int _key;
        private int _value;
        private int _accessCount;
        private LFUCacheEntry _next;
        private LFUCacheEntry _previous;

        LFUCacheEntry(int key, int value) {
            _key = key;
            _value = value;
            _accessCount = 1;

            _next = null;
            _previous = null;
        }
    }

    public h460_LFUCache(int capacity) {
        _capacity = capacity;
        _hashmap = new HashMap<Integer, LFUCacheEntry>(_capacity);
        _isFull = _capacity < 1;
        _head = null;
    }

    public int get(int key) {
        Integer oKey = Integer.valueOf(key);
        LFUCacheEntry entry = _hashmap.get(oKey);

        // not found
        if (null == entry) return -1;

        // re-prioritize by new frequency
        accessEntry(entry);

        validate();

        return entry._value;
    }

    public void set(int key, int value) {
        if (_capacity < 1) return;

        LFUCacheEntry entry = _hashmap.get(Integer.valueOf(key));
        if (null == entry) {
            // new entry
            entry = new LFUCacheEntry(key, value);

            if (_isFull) {
                // remove least recent used key
                _hashmap.remove(_head._key);
                _head = _head._next;
                if (null != _head) _head._previous = null;
            } else {
                _isFull = _capacity == _hashmap.size() + 1;
            }

            _hashmap.put(entry._key, entry);
            prioritize(entry, _head);
        } else {
            entry._value = value;
            accessEntry(entry);
        }

        validate();
    }

    private void accessEntry(LFUCacheEntry entry) {
        entry._accessCount++;
        if (null != entry._next && entry._next._accessCount <= entry._accessCount) {
            // remove entry from the linked list and connect previous to next
            if (_head == entry) {
                _head = entry._next;
            }
            LFUCacheEntry p = entry._previous;
            LFUCacheEntry q = entry._next;
            if (null != p) p._next = q;
            if (null != q) q._previous = p;
            entry._previous = null;
            entry._next = null;
            prioritize(entry, q);
        } // else do nothing as entry is already the last one
    }

    private void prioritize(LFUCacheEntry entry, LFUCacheEntry start) {
        if (null == entry || entry == start) return;

        if (null == start) {
            if (null == _head) _head = entry;
            return;
        }

        // entry will be inserted between p and q
        LFUCacheEntry p = start._previous;
        LFUCacheEntry q = start;

        while (null != q) {
            if (q._accessCount > entry._accessCount) {
                break;
            } else {
                p = q;
                q = p._next;
            }
        }

        // insert entry between p and q;
        entry._next = q;
        entry._previous = p;
        if (null != p) p._next = entry;
        if (null != q) q._previous = entry;

        if (null == entry._previous) _head = entry;
        //return start == entry._next ? entry : start;
    }

    private void validate() {
        if (_hashmap.size() > _capacity)
            throw new IllegalStateException("hashmap size is bigger than capacity.");

        LFUCacheEntry itor = _head;
        for (int i = 0; i < _hashmap.size(); ++i) {
            if (null == itor)
                throw new IllegalStateException("access count chain is less than _hashmap.");
            if (!_hashmap.containsKey(itor._key))
                throw new IllegalStateException("key " + itor._key + " does not exist.");
            if (null != itor._previous && itor._previous._next != itor)
                throw new IllegalStateException("access count chain broken.");
            if (null != itor._next && itor._next._previous != itor)
                throw new IllegalStateException("access count chain broken.");
            itor = itor._next;
        }
    }

    public static void main(String[] args) {
        test();

        test1();

        test2();
    }

    private static void test() {
        h460_LFUCache cache = new h460_LFUCache(2);
        cache.set(1, 1);
        cache.set(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.set(3, 3);                        // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3.
        cache.set(4, 4);                        // evicts key 1.
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }

    private static void test1() {
        test(new String[]{"LFUCache", "set", "get", "set", "get", "get"},
                new int[][]{{1}, {2, 1}, {2}, {3, 2}, {2}, {3}},
                new String[]{"null", "null", "1", "null", "-1", "2"});
    }

    private static void test2() {
        test(new String[]{"LFUCache", "set", "set", "set", "set", "set", "get", "set", "get", "get", "set", "get",
                        "set", "set", "set", "get", "set", "get", "get", "get", "get", "set", "set", "get",
                        "get", "get", "set", "set", "get", "set", "get", "set", "get", "get", "get", "set",
                        "set", "set", "get", "set", "get", "get", "set", "set", "get", "set", "set", "set",
                        "set", "get", "set", "set", "get", "set", "set", "get", "set", "set", "set", "set",
                        "set", "get", "set", "set", "get", "set", "get", "get", "get", "set", "get", "get",
                        "set", "set", "set", "set", "get", "set", "set", "set", "set", "get", "get", "get",
                        "set", "set", "set", "get", "set", "set", "set", "get", "set", "set", "set", "get",
                        "get", "get", "set", "set", "set", "set", "get", "set", "set", "set", "set", "set",
                        "set", "set"},
                new int[][]{{10}, {10, 13}, {3, 17}, {6, 11}, {10, 5}, {9, 10}, {13}, {2, 19}, {2}, {3}, {5, 25},
                        {8}, {9, 22},
                        {5, 5}, {1, 30}, {11},
                        {9, 12}, {7}, {5}, {8}, {9}, {4, 30}, {9, 3}, {9}, {10}, {10}, {6, 14}, {3, 1}, {3}, {10,
                        11}, {8}, {2, 14}, {1}, {5}, {4},
                        {11, 4}, {12, 24}, {5, 18}, {13}, {7, 23}, {8}, {12}, {3, 27}, {2, 12}, {5}, {2, 9}, {13, 4},
                        {8, 18}, {1, 7}, {6}, {9, 29},
                        {8, 21}, {5}, {6, 30}, {1, 12},

                        {10},

                        {4, 15}, {7, 22}, {11, 26}, {8, 17}, {9, 29}, {5}, {3,
                        4}, {11, 30}, {12}, {4, 29}, {3},
                        {9}, {6}, {3, 4}, {1}, {10}, {3, 29}, {10, 28}, {1, 20}, {11, 13}, {3}, {3, 12}, {3, 8}, {10,
                        9}, {3, 26}, {8}, {7}, {5}, {13,
                        17}, {2, 27}, {11, 15}, {12}, {9, 19}, {2, 15}, {3, 16}, {1}, {12, 17}, {9, 1}, {6, 19}, {4},
                        {5}, {5}, {8, 1}, {11, 7}, {5, 2}, {9, 28}, {1}, {2, 2}, {7, 4}, {4, 22}, {7, 24}, {9, 26},
                        {13, 28}, {11, 26}},
                new String[]{"null", "null", "null", "null", "null", "null", "-1", "null", "19", "17", "null", "-1",
                        "null", "null", "null", "-1", "null", "-1", "5", "-1", "12", "null", "null", "3", "5", "5",
                        "null", "null", "1", "null", "-1", "null", "30", "5", "30", "null", "null", "null", "-1",
                        "null", "-1", "24", "null", "null", "18", "null", "null", "null", "null", "14", "null",
                        "null", "18", "null", "null", "11", "null", "null", "null", "null", "null", "18", "null",
                        "null", "-1", "null", "4", "29", "30", "null", "12", "11", "null", "null", "null", "null",
                        "29", "null", "null", "null", "null", "17", "-1", "18", "null", "null", "null", "-1", "null",
                        "null", "null", "20", "null", "null", "null", "29", "18", "18", "null", "null", "null",
                        "null", "20", "null", "null", "null", "null", "null", "null", "null"});
    }

    private static void test(String[] ops, int[][] values, String[] expects) {
        h460_LFUCache cache = new h460_LFUCache(values[0][0]);
        for (int i = 1; i < ops.length; ++i) {
            if ("set".equals(ops[i])) {
                cache.set(values[i][0], values[i][1]);
            } else {
                int result = cache.get(values[i][0]);
                int expected = Integer.parseInt(expects[i]);
                if (result != expected) {
                    System.out.println(i + "\tkey: " + values[i][0] + "; expected:" + expected + "; result: " +
                            result + "\n");
                }
            }
        }
    }
}

