package edu.emory.cs.trie.autocomplete;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayDeque;
import edu.emory.cs.trie.*;

public class AutocompleteHW extends Autocomplete< List<String> > {
    //T is List<String>
    ArrayDeque<TrieNode<List<String>>> queue1;
    public AutocompleteHW(String dict_file, int max) {
        super(dict_file, max);
        queue1 = new ArrayDeque<>();
    }

    @Override
    public List<String> getCandidates(String prefix) {
        prefix = prefix.trim();
        List<String> output = new ArrayList<String>();
        TrieNode<List<String>> node = find(prefix);
        if (node == null) return output;
        List<String> prevCand = node.getValue();
        if (prevCand != null){
            output.addAll(prevCand);
        }
        queue1.clear();
        queue1.add(node);
        TrieNode<List<String>> temp;
        TrieNode<List<String>> child;
        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g',
                'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        // do a breadth first search and add to the list when find an endstate.
        while ( !queue1.isEmpty() && (output.size() < getMax()) ){
            temp = queue1.removeFirst();
            if (temp.isEndState()){
                if (!output.contains(constructWord(temp))){
                    output.add(constructWord(temp));
                }
            }
            for (char letter : alphabet){
                if (output.size() >= getMax()) return output;
                child = temp.getChild(letter);
                if (child != null){
                    queue1.addLast(child);
                }
            }
        }
        return output;
    }

    public String constructWord(TrieNode<List<String>> node){
        String output = "";
        while (node.getParent() != null) {
            output = node.getKey() + output;
            node = node.getParent();
        }
        return output;
    }

    @Override
    public void pickCandidate(String prefix, String candidate) {
        prefix = prefix.trim();
        candidate = candidate.trim();
        put(candidate, null); //adds candidate to tree if it wasn't there already
        TrieNode<List<String>> node = find(prefix);
        if (node == null){
            put(prefix, null);
            node = find(prefix);
            node.setEndState(false);
        }
        if (node.getValue() != null) {
            if (node.getValue().contains(candidate)) {
                node.getValue().remove(candidate); // no duplicates.
            }
            node.getValue().add(0, candidate);
            System.out.println(node.getValue());
        }
        if (node.getValue() == null) {
            List<String> candidates = new ArrayList<String>();
            candidates.add(candidate);
            node.setValue(candidates);
        }

    }

}