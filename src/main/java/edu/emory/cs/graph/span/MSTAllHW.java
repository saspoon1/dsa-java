package edu.emory.cs.graph.span;
import edu.emory.cs.graph.Edge;
import edu.emory.cs.graph.Graph;
import java.util.*;
public class MSTAllHW implements MSTAll {
    @Override
    public List<SpanningTree> getMinimumSpanningTrees(Graph graph){
        if (graph.getAllEdges().size() == 0){
            List<SpanningTree> output = new ArrayList<SpanningTree>();
            SpanningTree tree = new SpanningTree();
            Edge edge = new Edge(0, 0);
            tree.addEdge(edge);
            output.add(tree);
            return output;
        }
        List<SpanningTree> output = reduceList(function(graph));
        return output;
    }

    public List<SpanningTree> function(Graph graph){
        //create vars.
        List<SpanningTree> output = new ArrayList<SpanningTree>();
        PriorityQueue<Edge> pq, pq2;
        pq = new PriorityQueue<Edge>();
        SpanningTree tree = new SpanningTree();
        boolean[] visited = new boolean[ graph.size()];
        Edge edge;
        List<Edge> x = new ArrayList<Edge>();
        //give values
        visited[0] = true;
        pq.addAll( graph.getIncomingEdges(0) );
        pq2 = new PriorityQueue<Edge>(pq);
        if (pq2 == null){ return null; }
        x.add(pq2.poll());
        if (pq2.peek() != null){
            while (x.get(0).getWeight() == pq2.peek().getWeight()){
                x.add(pq2.poll());
                if (pq2.peek() == null) break; }
        }

        //go through all the smallest edges targeting zero.
        for (int i = 0; i < x.size(); i++){
            output.addAll(finishSpanningTree(graph, output, new PriorityQueue<Edge>(pq), new SpanningTree(tree), visited.clone(), x.get(i), 0) ); }
        return output; }
    public List<SpanningTree> finishSpanningTree(Graph graph, List<SpanningTree> output, PriorityQueue<Edge> pq, SpanningTree tree, boolean[] visited, Edge edge, int counter) {
        //Edge cases:
            if (counter > graph.size()) return output;
            if (edge ==  null) return output;
            if (visited[(edge.getSource())]) return output;
        // remove the selected edge from the pq
            pq.remove(edge);
            tree.addEdge(edge);
            visited[(edge.getSource())] = true;
        // if the spanning tree is complete, add it to the list of spanning trees.
        if ( tree.size() + 1 == graph.size() ) {
            output.add(tree);
            return output; }
        else {
            // find next edges to add to spanning tree
            for (Edge edge1 : graph.getIncomingEdges(edge.getSource())) {
                if (!visited[edge1.getSource()]) pq.add(edge1); }
            PriorityQueue<Edge> pq2 = new PriorityQueue<Edge>(pq);
            List<Edge> x = new ArrayList<Edge>();
            x.add(pq2.poll());
            if (pq2.peek() != null) {
                while (x.get(0).getWeight() == pq2.peek().getWeight()) {
                    x.add(pq2.poll());
                    if (pq2.peek() == null) break; } }
            // if there are multiple options, then use recursion to do them all.
            for (int i = 0; i < x.size(); i++) {
                edge = x.get(i);
                if (!visited[edge.getSource()])
                output.addAll(finishSpanningTree(graph, new ArrayList<SpanningTree>(), new PriorityQueue<Edge>(pq),
                        new SpanningTree(tree), visited.clone(), edge, counter + 1)); } }
        // return the list of spanning trees.
        return output;
    }

    public List<SpanningTree> reduceList(List<SpanningTree> input){
        if (input.size() == 0) return input;
        double smallestWeight;
        SpanningTree tree;
        //find the minimum spanning tree total weight
        smallestWeight = (input.get(0)).getTotalWeight();
        for(int index = 0; index < input.size(); index++){
            tree = input.get(index);
            if (tree == null) input.remove(index);
            else if (tree.getTotalWeight() < smallestWeight) {
                smallestWeight = tree.getTotalWeight(); }
        }
        //remove any trees that are not the minimum weight.
        for (SpanningTree tree1: input) {
            if (tree1.getTotalWeight() > smallestWeight) input.remove(tree1);
            //Collections.sort(tree1.getEdges());
        }

        return getRidOfDuplicates(input);
    }
    public List<SpanningTree> getRidOfDuplicates (List<SpanningTree> input){
        List<SpanningTree> output = new ArrayList<>();
        List<String> undirectedSequences = new ArrayList();
        output.add(input.get(0));
        undirectedSequences.add(input.get(0).getUndirectedSequence());
        for (SpanningTree tree:input) {
            if (!undirectedSequences.contains(tree.getUndirectedSequence())){
                output.add(tree);
                undirectedSequences.add(tree.getUndirectedSequence());
            }
        }
        return output;

    }
}