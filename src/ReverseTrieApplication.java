import java.util.ArrayList;

/**
 * utility class that contains utility methods using the data structure
 * this class can easily be extended to add more utility functions
 * @author chengkang xu
 */

public class ReverseTrieApplication {
	
	private ReverseTrie t;//build the trie
	private boolean lastInsertSucceed;
	
	ReverseTrieApplication (boolean case_sensitive, String [] rhymelist) {
		t =  new ReverseTrie(case_sensitive);//build the trie
		this.buildTrie(rhymelist);
	}
	
	/**
	 * build the reverse trie from the rhymelist
	 * @param rhymelist list of rhyme words
	 * @return whether the tree is built successfully
	 */
	
	
	public boolean buildTrie (String [] rhymelist) {
		if (rhymelist == null || rhymelist.length == 0) {
			System.out.println("invalid rhymelist");
			lastInsertSucceed = false;
			return false;
		}
		for (String s: rhymelist) {
			if (s != null && s.length() != 0)
				t.insert(s);
		}
		lastInsertSucceed = true;
		return true;
	}
	
	
	/**
	 * search target in the trie
	 * @param target target key
	 * @return similiar word
	 */
	
	public String findRhyme (String target) {
		if (target == null || target.length() == 0) {
			System.out.println("invalid target ");
			return null;
		}
		Result r = t.search(target);
		if (r != null)
			return r.selected;
		return null;
	}
	
	/**
	 * get the closest rhyming word pool
	 * @param target target word
	 * @return rhyming word pool 
	 */
	
	public ArrayList <String> getRhymeWordPool (String target) {
		if (target == null || target.length() == 0) {
			System.out.println("invalid target ");
			return null;
		}
		Result r = t.search(target);
		if (r != null)
			return r.subtrie;
		return null;
	}
	
	
	/**
	 * get whether last insertion was succeeded
	 * @return lastInsertSucceed
	 */
	public boolean getLastInsertSucceed () {
		return lastInsertSucceed;
	}
	
	
	/**
	 * print the trie
	 */
	public void printTrie() {
		t.print();
	}
	
}

