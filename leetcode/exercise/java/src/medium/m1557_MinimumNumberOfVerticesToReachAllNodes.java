package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.AbstractMap.SimpleEntry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 1557. Minimum Number of Vertices to Reach All Nodes
 * https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes/
 * 
 * Given a directed acyclic graph, with n vertices numbered from 0 to n-1, and
 * an array edges where edges[i] = [fromi, toi] represents a directed edge from
 * node fromi to node toi.
 * 
 * Find the smallest set of vertices from which all nodes in the graph are
 * reachable. It's guaranteed that a unique solution exists.
 * 
 * Notice that you can return the vertices in any order.
 * 
 * Constraints:
 * 
 * 2 <= n <= 10^5
 * 1 <= edges.length <= min(10^5, n * (n - 1) / 2)
 * edges[i].length == 2
 * 0 <= fromi, toi < n
 * All pairs (fromi, toi) are distinct.
 * 
 */
public class m1557_MinimumNumberOfVerticesToReachAllNodes {
    static m1557_MinimumNumberOfVerticesToReachAllNodes solution = new m1557_MinimumNumberOfVerticesToReachAllNodes();

    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        List<Integer> result = new ArrayList<>();
        int[] inDegree = new int[n];
        for (List<Integer> edge : edges) {
            inDegree[edge.get(1)]++;
        }
        for (int i = 0; i < inDegree.length; ++i) {
            if (inDegree[i] == 0)
                result.add(i);
        }
        return result;
    }

    public List<Integer> findSmallestSetOfVertices2(int n, List<List<Integer>> edges) {
        List<Integer> result = new ArrayList<>();
        // compute in degree for each vertex
        int[] inDegree = new int[n];
        HashMap<Integer, List<List<Integer>>> edgeMap = new HashMap<>();
        for (List<Integer> edge : edges) {
            inDegree[edge.get(1)]++;
            if (!edgeMap.containsKey(edge.get(0)))
                edgeMap.put(edge.get(0), new ArrayList<List<Integer>>());
            edgeMap.get(edge.get(0)).add(edge);
        }
        // SimpleEntry: key: inDegree, value: index of vertex
        PriorityQueue<SimpleEntry<Integer, Integer>> queue = new PriorityQueue<>(n,
                new Comparator<SimpleEntry<Integer, Integer>>() {
                    public int compare(SimpleEntry<Integer, Integer> left, SimpleEntry<Integer, Integer> right) {
                        if (left.getKey() == right.getKey())
                            return 0;
                        else if (left.getKey() < right.getKey())
                            return -1;
                        else
                            return 1;
                    }
                });
        for (int i = 0; i < inDegree.length; ++i) {
            queue.add(new SimpleEntry<Integer, Integer>(inDegree[i], i));
        }
        // travese
        Set<Integer> vertices = new HashSet<Integer>(Arrays.stream(
                IntStream.rangeClosed(0, n - 1).toArray()).boxed().collect(Collectors.toList()));
        while (!vertices.isEmpty()) {
            SimpleEntry<Integer, Integer> v = queue.remove();
            Integer current = v.getValue();
            result.add(current);
            vertices.remove(current);
            // traverse from v;
            Queue<Integer> tq = new LinkedList<>();
            tq.add(current);
            while (!tq.isEmpty()) {
                current = tq.remove();
                if (edgeMap.containsKey(current)) {
                    for (List<Integer> e : edgeMap.get(current)) {
                        Integer next = e.get(1);
                        if (vertices.contains(next)) {
                            vertices.remove(next);
                            tq.add(next);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void test(int n, List<List<Integer>> edges) {
        solution.findSmallestSetOfVertices(n, edges);
    }

    public static void main(String[] args) {
        test(6, List.of(
                List.of(0, 1),
                List.of(0, 2),
                List.of(2, 5),
                List.of(3, 4),
                List.of(4, 2)));

        test(5, List.of(
                List.of(0, 1),
                List.of(2, 1),
                List.of(3, 1),
                List.of(1, 4),
                List.of(2, 4)));
    }
}
