

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Trienode class that is a basic node units in trie data structure
 * @author chengkang xu
 */

class TrieNode {
	private HashMap <Character, TrieNode> children;//char key to node mapping
	private HashMap <Character, StringBuilder> children_string;
	private boolean completed;	//whether this node is completed
	
	
	/**
	 * constructor that initialized both mappings and set completed 
	 * @param completed whether this node is completed
	 */
	TrieNode (boolean completed) {
		this.completed = completed;
		children = new HashMap <> ();
		children_string = new HashMap <> ();
	}
	
	/**
	 * add a child to current node mappings.
	 * @param newchild key for new child
	 * @param in string new child associates with
	 * @param completed whether the new child is a complete word
	 */
	
	void addChild (char newchild, StringBuilder in, boolean completed) {
		if (!children.containsKey(newchild)) {
			
			children.put(newchild,new TrieNode(completed));
			children_string.put(newchild, in);
		}
	}
	
	/**
	 * check whether current node has no child
	 * @return whether current node has no child
	 */
	
	boolean isLeaf () {
		return children_string.size() == 0;
	}
	
	/**
	 * check if current node has child associate with given key
	 * @param in
	 * @return whether current node has that child
	 */
	
	boolean hasChild(char in) {
		return children.containsKey(in);
	}
	
	/**
	 * get the node associate with given key
	 * @param in string key
	 * @return node associate with the key
	 */
	
	TrieNode getChild (char in) {
		if (children.containsKey(in)) {
			return children.get(in);
		}
		return null;
	}
	
	/**
	 * return the children node mapping 
	 * @return children node mapping
	 */
	
	HashMap <Character, TrieNode> getChildren () {
		return children;
	}
	
	/**
	 * return the children string mapping
	 * @return children string mapping
	 */
	
	HashMap <Character, StringBuilder> getChildrenString () {
		return children_string;
	}
	
	/**
	 * set child node associate with give key 
	 * @param in string key
	 * @param newnode new child node
	 */
	
	void setChild (char in, TrieNode newnode) {
		children.put(in, newnode);
	}
	
	/**
	 * get specific string given key string
	 * @param in string key
	 * @return specific string associate with the key
	 */
	
	StringBuilder getChildString (char in) {
		if (children_string.containsKey(in)) {
			return children_string.get(in);
		}
		return null;
	}
	
	/**
	 * set the string associate with the key
	 * @param in string key
	 * @param newstring new string associate with the key
	 */
	
	void setChildString (char in, StringBuilder newstring) {
		children_string.put(in, newstring);
	
	}
	
	/**
	 * return whether current node is completed
	 * @return
	 */
	boolean isCompleted () {
		return completed;
	}

	/**
	 * set whether the current node is completed
	 * @param completed boolean value
	 */
	void setCompleted (boolean completed) {
		this.completed = completed;
	}
	
	/**
	 * print the children map of this node
	 * @param level print levels
	 */
	
	void printNode(int level) {
	  	
        for (Entry <Character, TrieNode> e : children.entrySet()) {
        		System.out.println (" level " + level + " " + e.getKey() + " " + e.getValue().isCompleted());
 
        		 if (this.getChild(e.getKey()) != null)
        			 this.getChild(e.getKey()).printNode(level + 1);
        }
    }
	
	/**
	 * print the children_string map of this node
	 * @param level print levels
	 * @param parent parent of the current node
	 */

    void printString(int level, String parent) {
  	
        for (Entry <Character, StringBuilder> e : this.children_string.entrySet()) {
        		System.out.println ("parent " + parent + " level " + level + " " 
        				+ e.getKey() + " " 
        				+ e.getValue().toString() + " " 
        				+ this.getChild(e.getKey()).isCompleted());
 
        		 if (this.getChild(e.getKey()) != null)
        			 this.getChild(e.getKey()).printString(level + 1, e.getValue().toString());
        }

    }
	
}