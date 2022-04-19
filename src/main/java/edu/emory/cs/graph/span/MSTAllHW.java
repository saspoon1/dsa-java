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
        return output; }
    public List<SpanningTree> reduceList(List<SpanningTree> input){
        if (input.size() == 0) return input;
        double smallestWeight;
        SpanningTree tree;
        smallestWeight = (input.get(0)).getTotalWeight();
        int smallestWeightIndex = 0;
        for(int index = 0; index < input.size(); index++){
            tree = input.get(index);
            if (tree == null) input.remove(index);
            else if (tree.getTotalWeight() < smallestWeight) {
                smallestWeight = tree.getTotalWeight();
                smallestWeightIndex = index;
            }
            else if (tree.getTotalWeight() > smallestWeight) input.remove(index);
        }
        for (SpanningTree tree1: input) {
            if (tree1.getTotalWeight() > smallestWeight) input.remove(tree1);
        }
        return getRidOfDuplicates(input);
    }
    public List<SpanningTree> getRidOfDuplicates (List<SpanningTree> input){
        List<SpanningTree> output = new ArrayList<>();
        output.add(input.get(0));
        for (int i = 1; i < input.size(); i ++){
            if (!output.contains(input.get(i))) output.add(input.get(i));
        }
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
        if (pq2 == null){return null;}
        x.add(pq2.poll());
            while (x.get(0).getWeight() == pq2.peek().getWeight()){
                x.add(pq2.poll());
                if (pq2.peek() == null) break; }
        //go through all the smallest edges targeting zero.
        for (int i = 0; i < x.size(); i++){
            output.addAll(finishSpanningTree(graph, output, pq, new SpanningTree(tree), visited.clone(), x.get(i), 0) ); }
        return output; }

    public List<SpanningTree> finishSpanningTree(Graph graph, List<SpanningTree> output, PriorityQueue<Edge> pq, SpanningTree tree, boolean[] visited, Edge edge, int counter) {
        //Edge cases:
            if (counter > graph.size()) return null;
            if (edge ==  null) return null;
            if (visited[(edge.getSource())]) return null;
        // remove the selected edge from the pq
            pq.remove(edge);
            tree.addEdge(edge);
            visited[(edge.getSource())] = true;
            for (Edge edge1 : graph.getIncomingEdges(edge.getSource())) {
                if (!visited[edge1.getSource()]) pq.add(edge1); }
        // if the spanning tree is complete, add it to the list of spanning trees.
        if ( tree.size() + 1 == graph.size() ) {
            List<SpanningTree> newTree = new ArrayList<SpanningTree>();
            newTree.add(tree);
            return newTree; }
        else {
            // find next edges to add to spanning tree
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
                output.addAll(finishSpanningTree(graph, output, pq, new SpanningTree(tree), visited.clone(), edge, counter + 1)); } }
        // return the list of spanning trees.
        return output;
    }
}