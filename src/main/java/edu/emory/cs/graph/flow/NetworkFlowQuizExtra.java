package edu.emory.cs.graph.flow;
import edu.emory.cs.graph.Graph;
import edu.emory.cs.graph.Subgraph;
import edu.emory.cs.graph.Edge;
import edu.emory.cs.graph.span.SpanningTree;

import java.util.*;

public class NetworkFlowQuizExtra {
    public Set<Subgraph> getAugmentingPaths(Graph graph, int source, int target) {
        Set<Subgraph> output = new HashSet<Subgraph>();
        List<Edge> list;
        Subgraph subgraph = new Subgraph();
        Set<Integer> visited = new HashSet<Integer>();

        Deque<Subgraph> queue = new ArrayDeque<Subgraph>();
        int vertex;

        list = graph.getIncomingEdges(target);
        for(Edge edge : list){
            queue.add(newSubgraph(subgraph, edge));
        }


        while (queue.size() != 0){
            subgraph = queue.poll();
            list = subgraph.getEdges();
            vertex = subgraph.getEdges().get(list.size()-1).getSource();
            if (vertex == source){
                output.add(subgraph);
            }
            visited = getVisited(subgraph);
            list = graph.getIncomingEdges(vertex);
            for(Edge edge : list){
                if (!visited.contains(edge.getSource()))
                    queue.addLast(newSubgraph(subgraph, edge));
            }
        }

        return output;
    }



    public Subgraph newSubgraph (Subgraph subgraph, Edge edge){
        Subgraph output = new Subgraph (subgraph);
        output.addEdge(edge);
        return output;
    }

    public Set<Integer> getVisited(Subgraph subgraph){
        Set<Integer> visited = new HashSet<>();
        Edge edge;
        List<Edge> list1 = subgraph.getEdges();
        for (int i = 0; i < list1.size(); i++){
            edge = list1.get(i);
            visited.add( edge.getTarget() );
        }
        int last = list1.size()-1;
        visited.add(list1.get(last).getSource());
        return visited;
    }

    public static void main(String[] args) {
        NetworkFlowQuizExtra m = new NetworkFlowQuizExtra();
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
