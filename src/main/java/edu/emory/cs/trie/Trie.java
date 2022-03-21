package edu.emory.cs.trie;

public class Trie<T> {

    private final TrieNode<T> root;

    public Trie(){
        //initialize the root with the null character
        root = new TrieNode<> (null, (char)0);
    }

    /** @return the node with the specific key if exists; otherwise, {@code null}. */
    public TrieNode<T> find(String key){
        TrieNode<T> node = root;

        //iterates through every character in the string
        for (char c: key.toCharArray()){
            //finds the node whose key is the current character
            node = node.getChild(c);
            //if the child does not exist, implying that key is not introduced in this trie.
            if(node == null) return null;
        }

        return node;
    }
    public T get(String key){
        TrieNode<T> node = find(key);
        //checks if the key exists and the key is a word
        return (node != null && node.isEndState()) ? node.getValue() : null;
    }
    public boolean contains(String key){
        return get(key) != null;
    }
    //insets a key-value pair to the trie.
    public T put(String key, T value){
        TrieNode<T> node = root;
        //iterate through all the characters in the key and add children as necessary
        for (char c: key.toCharArray())
            node = node.addChild(c);
        //sets the end state of the node corresponding the last character to true
        node.setEndState(true);
        //returns the value of the previously key if exists; otherwise null
        return node.setValue(value);
    }
    public boolean remove(String key){
        //retrieves the node corresponding to the last character in the key
        TrieNode<T> node = find(key);
        if (node == null || !node.isEndState())
            //returns false if the key does not exist or the key is not a word
            return false;
        //if the retrieved node has children, set its end-state to false and return true
        if(node.hasChildren()){
            node.setEndState(false);
            return true;
        }
        //removes ancestors who have descendants only related to this key.
        TrieNode<T> parent = node.getParent();
        while(parent != null){
            parent.removeChild(node.getKey());
            if(parent.hasChildren() || parent.isEndState())
                break;
            else{
                node = parent;
                parent = parent.getParent();
            }
        }

        return true;
    }

}
