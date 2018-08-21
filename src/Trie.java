

/**
 * abstract Trie class that provides general structure to different implementation of Trie
 * @author chengkang xu
 */

public abstract class Trie {
	
	private TrieNode head;//head of the trie
	private boolean case_sensitive;//whether the trie is case sensitive
	
	public Trie (boolean case_sensitive) {
		this.head = new TrieNode (false);
		this.case_sensitive = case_sensitive;
	}
	
	abstract void insert (String key); //abstract insert method 
	abstract Object search (String key);//abstract search method
	
	/**
	 * print the entire trie
	 * @param 
	 * @return
	 */
	
	void print() {
        head.printString(1, "0");
        head.printNode(1);
    }
	
	/**
	 * return whether the Trie is case sensitive
	 * @return case_sensitive
	 */
	boolean isCaseSensitive () {
		return case_sensitive;	
	}
	
	/**
	 * get head
	 * @return head
	 */
	TrieNode getHead () {
		return head;
	}
}

