package edu.emory.cs.trie;

import java.util.ArrayList;
import java.util.List;

/*
 Update the getEntities() method that takes an input string
 and returns the list of Entity,
 where each entity consists of the begin index (inclusive)
 and end index (exclusive) of the first and the last characters
 of the corresponding country name respectively as well as
 the ID of the country name.
 */



public class TrieQuiz extends Trie<Integer> {
    /**
     * PRE: this trie contains all country names as keys and their unique IDs as values
     * (e.g., this.get("United States") -> 0, this.get("South Korea") -> 1).
     * @param input the input string in plain text
     *              (e.g., "I was born in South Korea and raised in the United States").
     * @return the list of entities (e.g., [Entity(14, 25, 1), Entity(44, 57, 0)]).
     */
    Trie<Integer> countriesTrie = new Trie();
    public List<Entity> getEntities(String input) {
        List<Entity> output = new ArrayList<Entity>();
        String temp;
        TrieNode<Integer> node;
        Integer value;
        System.out.println(input.length());
        for (int i = 0; i < input.length(); i++){
            for (int j = i+1; j <= input.length(); j++){ // n + (n-1) + (n-2) + ... + 1 -> n*(n+1)/2 -> O(n^2)
                temp = input.substring(i, j); //O(n) where n = j-1
                node = find(temp); //O(n) where n = j-i
                if (node == null) break;
                value = (node != null && node.isEndState()) ? node.getValue() : null; //O(1)
                if (value != null){
                    output.add(new Entity(i, j, value) );
                    i = j;
                    break;
                }// n+n+1 -> 2n+1 -> O(n)
            }
        } // -> n*n^2 -> n^3.
        return output;
    }
}