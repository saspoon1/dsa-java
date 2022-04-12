package edu.emory.cs.graph.span;

import edu.emory.cs.graph.Edge;
import edu.emory.cs.graph.Graph;
import edu.emory.cs.set.DisjointSet;

import java.util.List;
import java.util.PriorityQueue;

public class MSTKruskal implements MST{
    @Override
    public SpanningTree getMinimumSpanningTree(Graph graph){
        PriorityQueue<Edge> queue = new PriorityQueue<>(graph.getAllEdges());
        DisjointSet forest = new DisjointSet(graph.size());
        SpanningTree tree = new SpanningTree();

        while(!queue.isEmpty()){                                        //the queue contains all edges.
            Edge edge =  queue.poll();
            if(!forest.inSameSet(edge.getTarget(), edge.getSource())){  // if the vertices are not in the same disjoint set
                tree.addEdge(edge);                                         // add edge to spanning tree
                if(tree.size() + 1 == graph.size()) break;                  // if tree # edges is one less than graph # vertices, break
                forest.union(edge.getTarget(), edge.getSource());           // put the vertices in same disjoint set
            }
        }
        return tree;
    }

    public static void main(String[] args) {
        Graph g = new Graph(3);
        g.setUndirectedEdge(0, 1, 0);
        g.setUndirectedEdge(0, 2, 0);
        g.setUndirectedEdge(1, 2, 0);

        List<Edge> x = g.getAllEdges();
        System.out.println(x);
        MSTKruskal m = new MSTKruskal();
        SpanningTree y = m.getMinimumSpanningTree(g);
        System.out.println(y);
        System.out.println();


    }
}
