package edu.emory.cs.trie;
import java.util.Map;
import java.util.HashMap;

public class TrieNode<T> { //the generic type T represents the type of value
    //Map is used to store the children
    private final Map<Character, TrieNode<T>> children;
    private TrieNode<T> parent;
    //endstate is true if the node represents the last character of a word. otherwise, false
    private boolean end_state;
    private char key;
    private T value;

    public TrieNode(TrieNode<T> parent, char key){
        //hashmap gives the O(1) complexity for searching a key.
        children = new HashMap<>();
        setEndState(false);
        setParent(parent);
        setKey(key);
        setValue(null);
    }

/**
 * Getters
 */

    public TrieNode<T> getParent(){
        return parent;
    }
    public char getKey(){
        return key;
    }
    public T getValue(){
        return value;
    }
    public TrieNode<T> getChild(char key){
        return children.get(key);
    }
    /** @return the map whose keys and values are children's characters and nodes. */
    //returns the map consisting of the children's characters as keys and their nodes as values.
    public Map<Character, TrieNode<T>> getChildrenMap(){
        return children;
    }
/**
 * Setters
 */

    public void setParent(TrieNode<T> node){
        parent = node;
    }
    public void setKey(char key){
        this.key = key;
    }
    public void setEndState(boolean endState){
        end_state = endState;
    }
    //returns the previously assigned value of this node
    public T setValue(T value){
        T tmp = this.value;
        this.value = value;
        return tmp;
    }
    //returns the child with the specific key if exists
    //otherwise a new child with the key
    public TrieNode<T> addChild(char key){
        TrieNode<T> child = getChild(key);
        if (child == null){
            child = new TrieNode<> (this, key);
            children.put(key, child);
        }
        return child;
    }
    public TrieNode<T> removeChild(char key){
        return children.remove(key);
    }
/**
 * Checkers
 */
    //returns true if this node represents the last character of a certain word
    public boolean isEndState(){
        return end_state;
    }
    public boolean hasValue(){
        return value != null;
    }
    public boolean hasChildren(){
        return !children.isEmpty();
    }
}
