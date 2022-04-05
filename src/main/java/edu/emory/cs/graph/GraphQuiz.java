package edu.emory.cs.graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GraphQuiz extends Graph {
    public GraphQuiz(int size) { super(size); }
    public GraphQuiz(Graph g) { super(g); }

    /** @return the total number of cycles in this graph. */
    public int numberOfCycles(){
        int output = 0;
        // Do a DFS.
        // using an array of booleans with the index of the array corresponding to the vertex
        // which is kept track of by the list of lists.
        boolean[] visitedNodes = new boolean[size()];
        //going to the node represented by position 0 first
        output += numberOfCycles(0, visitedNodes, -1);
        Deque<Integer> extras = getVerticesWithNoIncomingEdges();
        while (!extras.isEmpty()){
            output += numberOfCycles(extras.pop(), visitedNodes, -2);
        }
        for (int i = 0; i < visitedNodes.length; i++){
            if (!visitedNodes[i]) output += numberOfCycles(i, visitedNodes, -1);
        }
        return output;
    }
    public int numberOfCycles(int index, boolean[] visitedNodes, int previousNode){
        int output = 0;
        if(visitedNodes[index] == true){return 0;}
        List<Deque<Edge>> outgoingEdges = getOutgoingEdges();
        Edge[] nodeEdges = {};
        nodeEdges = outgoingEdges.get(index).toArray(nodeEdges);
        if (previousNode == -2){return (nodeEdges.length + 1)/2;}
        if (nodeEdges.length != 0) {visitedNodes[index] = true;}
        // Do a DFS
        // select node represented by position index.
        for (int i = 0; i < nodeEdges.length; i++){
            Edge edge = nodeEdges[i];
            if ( !visitedNodes[edge.getTarget()])
                output += numberOfCycles( edge.getTarget(), visitedNodes, index );
            else {
                output += 1;
            }
        }
        return output;
    }

}