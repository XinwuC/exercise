package medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import easy.e292_NimGame;

/**
 * 133. Clone Graph
 * 
 * Total Accepted: 66300 Total Submissions: 266496 Difficulty: Medium
 * 
 * Clone an undirected graph. Each node in the graph contains a label and a list
 * of its neighbors.
 * 
 * OJ's undirected graph serialization: Nodes are labeled uniquely.
 * 
 * We use # as a separator for each node, and , as a separator for node label
 * and each neighbor of the node. As an example, consider the serialized graph
 * {0,1,2#1,2#2,2}.
 * 
 * The graph has a total of three nodes, and therefore contains three parts as
 * separated by #.
 * 
 * First node is labeled as 0. Connect node 0 to both nodes 1 and 2. Second node
 * is labeled as 1. Connect node 1 to node 2. Third node is labeled as 2.
 * Connect node 2 to node 2 (itself), thus forming a self-cycle.
 *
 */
public class m133_CloneGraph {
	class UndirectedGraphNode {
		int label;
		List<UndirectedGraphNode> neighbors;

		UndirectedGraphNode(int x) {
			label = x;
			neighbors = new ArrayList<UndirectedGraphNode>();
		}
	}

	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		class Pair {
			UndirectedGraphNode _clone;
			UndirectedGraphNode _reference;
			
			public Pair(UndirectedGraphNode clone, UndirectedGraphNode reference) {
				_clone = clone;
				_reference = reference;
			}
		}
		
        if (null == node) return null;
        
        HashMap<Integer, UndirectedGraphNode> _cloned = new HashMap<Integer, UndirectedGraphNode>();
        UndirectedGraphNode root = new UndirectedGraphNode(node.label);
    	_cloned.put(new Integer(root.label), root);
    	
        Queue<Pair> _unfinished = new LinkedList<Pair>();
        _unfinished.add(new Pair(root, node));
        
        Pair current = _unfinished.poll();
        while(null != current) {
        	for(UndirectedGraphNode neighbour: current._reference.neighbors) {
        		Integer key = new Integer(neighbour.label);
                if (_cloned.containsKey(key)) {
                    current._clone.neighbors.add(_cloned.get(key));
                } else {
                	UndirectedGraphNode cloneNeighbour = new UndirectedGraphNode(neighbour.label);
                	_cloned.put(new Integer(cloneNeighbour.label), cloneNeighbour);
                	current._clone.neighbors.add(cloneNeighbour);
                	_unfinished.add(new Pair(cloneNeighbour, neighbour));
                }
            }
        	
        	current = _unfinished.poll();
        }
       
        return root;
    }

	public static void main(String[] args) {
		int input = 1348820612;
		System.out.println("Input: " + input);

		e292_NimGame game = new e292_NimGame();
		long start = System.currentTimeMillis();
		boolean result = game.canWinNim(input);
		long end = System.currentTimeMillis();
		System.out.println("Output: " + result);
		System.out.println("Time: " + TimeUnit.MILLISECONDS.toSeconds(end - start));

	}

}
