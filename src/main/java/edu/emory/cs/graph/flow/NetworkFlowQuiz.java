package edu.emory.cs.graph.flow;

import edu.emory.cs.graph.Graph;
import edu.emory.cs.graph.Subgraph;
import edu.emory.cs.graph.Edge;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NetworkFlowQuiz {
    public Set<Subgraph> getAugmentingPaths(Graph graph, int source, int target) {
        Set<Subgraph> output = new HashSet<Subgraph>();
        List<Edge> list = graph.getIncomingEdges(target);
        Subgraph subgraph = new Subgraph();
        Set<Integer> visited = new HashSet<Integer>();
        visited.add(target);
        for (Edge edge : list){
            output.addAll(aux(graph, source, edge.getSource(), newSubgraph(subgraph, edge), new HashSet<>(output), new HashSet<Integer>(visited)));
        }
        return output;
    }

    public Subgraph newSubgraph (Subgraph subgraph, Edge edge){
        Subgraph output = new Subgraph (subgraph);
        output.addEdge(edge);
        return output;
    }

    public Set<Subgraph> aux(Graph graph, int source, int target, Subgraph subgraph, Set<Subgraph> set, Set<Integer> visited){
        visited.add(target);
        List<Edge> list = graph.getIncomingEdges(target);

        if (list.size() == 0){return set;}
        for (Edge edge : list) {
            if (!visited.contains(edge.getSource())) {
                newSubgraph(subgraph, edge);
                if (edge.getSource() == source){
                    set.add(newSubgraph(subgraph, edge));
                }
                set.addAll( aux(graph, source, edge.getSource(), newSubgraph(subgraph, edge), new HashSet<>(set), new HashSet<Integer>(visited)));
            }
        }
        return set;
    }

    public static void main(String[] args) {
        NetworkFlowQuiz m = new NetworkFlowQuiz();
        Graph graph = new Graph(6);
        int s = 0, t = 5;

        graph.setDirectedEdge(s, 1, 4);
        graph.setDirectedEdge(s, 2, 2);
        graph.setDirectedEdge(1, 3, 3);
        graph.setDirectedEdge(2, 3, 2);
        graph.setDirectedEdge(2, 4, 3);
        graph.setDirectedEdge(3, 2, 1);
        graph.setDirectedEdge(3, t, 2);
        graph.setDirectedEdge(4, t, 4);

        Set<Subgraph> x = m.getAugmentingPaths(graph, s, t);
        System.out.println(x);


    }

}
