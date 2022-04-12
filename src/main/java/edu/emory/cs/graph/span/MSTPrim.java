package edu.emory.cs.graph.span;

import edu.emory.cs.graph.Edge;
import edu.emory.cs.graph.Graph;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

public class MSTPrim implements MST{
    @Override
    public SpanningTree getMinimumSpanningTree(Graph graph){
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        SpanningTree tree = new SpanningTree();
        Set<Integer> visited = new HashSet<>();
        Edge edge;

        add(queue, visited, graph, 0);                  // adds all the edges connected to 0 to the queue.
        while(!queue.isEmpty()){
            edge = queue.poll();
            if (!visited.contains(edge.getSource())){          // if the source of the edge has not been visited
                tree.addEdge(edge);                                 // add edge to the spanning tree
                if(tree.size() + 1 == graph.size()) break;          // if the number of edges in the spanning tree is 1 less than the number of vertices in the graph, break
                add(queue, visited, graph, edge.getSource());       // add all the edges connected to the vertex
            }
        }
        return tree;
    }
    private void add(PriorityQueue<Edge> queue, Set<Integer> visited, Graph graph, int target){
        visited.add(target);                                            //add target to the visited set
        for (Edge edge : graph.getIncomingEdges(target)) {              //go through all the incoming edges
            if (!visited.contains(edge.getSource())) queue.add(edge);   //if the source is not in the visited set, add the edge to the priority queue
        }
    }

    private void add_using_lambda(PriorityQueue<Edge> queue, Set<Integer> visited, Graph graph, int target) {
        visited.add(target);

        graph.getIncomingEdges(target).stream().
                filter(edge -> !visited.contains(edge.getSource())).
                collect(Collectors.toCollection(() -> queue));
    }

    public static void main(String[] args) {
        Graph g = new Graph(3);
        g.setUndirectedEdge(0, 1, 0);
        g.setUndirectedEdge(0, 2, 0);
        g.setUndirectedEdge(1, 2, 0);
        List<Edge> x = g.getAllEdges();
        System.out.println(x);
        MSTPrim m = new MSTPrim();
        SpanningTree y = m.getMinimumSpanningTree(g);
        System.out.println(y);
        System.out.println();


    }
}