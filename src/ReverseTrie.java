

import java.util.*;
import java.util.Map.Entry;

/**
 * reverse Trie data structure class that stores and searches strings backwards
 * using TrieNode class as basic units. Extends from Trie abstract class
 * @author chengkang xu
 */

class ReverseTrie extends Trie{


	/**
	 *  constructor that initialize head
	 *  @param case_sensitive whether the trie is case sensitive
	 */
	ReverseTrie (boolean case_sensitive) {
		super (case_sensitive);
	}
	
	/**
	 * reverse the given string and return a stringbuilder
	 * @param input string to be reversed
	 */
	
	StringBuilder reverse (String key) {
		if (key == null) {
			return null;
		}
		if (!super.isCaseSensitive()) {
			return new StringBuilder(key.toLowerCase()).reverse();
		}
		return new StringBuilder(key).reverse();
	}
	
	/**
	 * insert a new key into trie using compressed node method
	 * starting with the first character in the key, check if the character exists
	 * in the map. If it exists, retrieve the partial string from the map and compare it 
	 * with the input key. If there is a difference between two string, add a new node 
	 * to store current key, and modify the current node to store the remainder of previous key
	 * 
	 * @param input string to be stored
	 */
	
	void insert (String key) {
	
		StringBuilder reversekey = reverse(key);//reverse the key
		
		int rlen = reversekey.length();//the length of the input key
		TrieNode temp = super.getHead();//make a temporary head
		int i = 0;//iterating through new word
		while( i < rlen) {
	
			char cur = reversekey.charAt(i);//get the char at current index
		
			StringBuilder sb = temp.getChildString(cur);//old string
			if (sb != null) {
				int j = 0;//iterating through old key
				int slen = sb.length();//the length of the old partial string
				
				//iterating through strings, stops either hit ends or difference
				while (i < rlen
						&& j < slen
						&& sb.charAt(j) == reversekey.charAt(i)) {
					i ++;
					j ++;
				}
				
				//if old string is not completed
				
				if (j < slen) {
					
					TrieNode rem = temp.getChild(cur);//get temp of current child
					
					//get substring form old string thats differ from the new string
					StringBuilder oldnodestring = new StringBuilder(sb.substring(j));
					
					char firstcharold = oldnodestring.charAt(0);//first char of old string
					
					//modify the original string to be the the matching substring
					sb.setLength(j);
					temp.setChildString(cur, sb);
					
					// need to add a branch since both words are not finished
					if (i < rlen) {

						//get substring form new string thats differ from the old string
						StringBuilder newnodestring = new StringBuilder(reversekey.substring(i));
						char firstcharnew = newnodestring.charAt(0);//first char of new string
						
						TrieNode newchild = new TrieNode(false);//create a new node connect two new nodes
			
						//store the two new partial nodes into the new node
						newchild.setChildString(firstcharnew, newnodestring);
						newchild.setChild(firstcharnew, new TrieNode(true));
						
						newchild.setChildString(firstcharold, oldnodestring);
						newchild.setChild(firstcharold, rem);
	
						//store the new node into the original map
						temp.setChild(cur, newchild);
						
					}
					//new word is shorter than old word, adding a prefix
					else {
						
						TrieNode newchild = new TrieNode(true);//create a new node
			
						newchild.setChildString(firstcharold, oldnodestring);
						newchild.setChild(firstcharold, rem);
	
						temp.setChild(cur, newchild);
					}	
					return;
				}
				
				else {
					//if old word is shorter than new word, keep searching
					temp = temp.getChild(cur);
				}
			}
			
			//if the word is new, break
			else {
				break;
			}
		}
		
		//we found a common prefix
		if (i == rlen) {
		
			temp.setCompleted(true);
		}
		//the word does not exist, add a new node
		else {
			temp.addChild(reversekey.substring(i).charAt(0), 
					new StringBuilder(reversekey.substring(i)),
					true);
		}
	}
	
	/**
	 * using the similar method as in insert. traverse through trie and compare
	 * input strings with the strings stored in trie. If found a match, return 
	 * a randomly select string from the subtree.
	 * @param input string to be checked
	 */
	
	Result search (String key) {
		
		StringBuilder reversekey = reverse(key);//reverse the key
		int rlen = reversekey.length();//length of the input key
		TrieNode temp = super.getHead();//make a temp of head

		int i = 0;//iterating through new word
		StringBuilder match = new StringBuilder();
		int level = 0;
		boolean hit = false;
		while( i < rlen) {
			char cur = reversekey.charAt(i);
			StringBuilder sb = temp.getChildString(cur);//old string
		
			if (sb != null) {
				
				int j = 0;//iterating through old key
				int slen = sb.length();
				
				//iterating through current strings until hit ends or difference
				while (i < rlen && j < slen && reversekey.charAt(i) == sb.charAt(j)) {
					i ++; 
					j ++;
					hit = true;
				}
				
				//if there is mismatch return the subtree
				if (j < slen) {
					match.append(sb);//append the current partial string
					return randomSelect(match.toString(), temp.getChild(cur));

				}
				else {
					//append the current partial string, and keep searching
			
					match.append(temp.getChildString(cur));
					temp = temp.getChild(cur);
					level ++;
				}
			}
			else {
				break;
			}
		}
		//we found a common prefix
		if (hit) {
			return randomSelect(match.toString(), temp);
		}
		
		return null;
	}
	
	
	/**
	 * randomly select a string from the subtree
	 * @param prefix prefix to be added to the string from subtree
	 * @param temp root of the subtree
	 * @return string selected
	 */
	
	Result randomSelect (String prefix, TrieNode temp) {
		
		ArrayList <String> subtree =  getSubTree(temp);//subtree rooted at temp
		//printSubTree(subtree, prefix);
		
		//if temp has subtree, pick one string from it and append it to prefix
		if (subtree.size() > 0) {
			Random rand = new Random();
			int n = rand.nextInt(subtree.size());
			StringBuilder sb = new StringBuilder(prefix + subtree.get(n));
			return new Result(addPrefixToSubtree(subtree, prefix), sb.reverse().toString());
		}
		return null;
	}
	
	/**
	 * get subtree rooted at curnode using recursion
	 * @param curnode root of the subtree
	 * @return the subtree strings rooted at curnode
	 */
	
	ArrayList <String> getSubTree (TrieNode curnode) {
		ArrayList <String> res = new ArrayList <> ();//store the result subtree strings
		StringBuilder prev = new StringBuilder("");//store the partial strings
		subtreehelper(res, prev, curnode);//recursion call
		return res;
	}
	
	/**
	 * print the content in subtree
	 * @param subtree subtree to be printed
	 * @param prefix prefix to be added to subtree
	 */
	
	void printSubTree (ArrayList <String> subtree, String prefix) {
		System.out.print("[");
		for (int i = 0; i < subtree.size(); i ++) {
			String word = subtree.get(i);
			StringBuilder sb = new StringBuilder(prefix + word);
			System.out.print( sb.reverse().toString());
			if (i < subtree.size() - 1)
				System.out.print(" ");
		}
		System.out.println("]");
	}
	
	/**
	 * add prefix to item in subtree 
	 * @param subtree subtree to be added
	 * @param prefix prefix
	 * @return new list of nodes
	 */
	
	ArrayList <String> addPrefixToSubtree (ArrayList <String> subtree, String prefix) {
		ArrayList <String> res = new ArrayList <> ();
		for (int i = 0; i < subtree.size(); i ++) {
			String word = subtree.get(i);
			StringBuilder sb = new StringBuilder(prefix + word);
			res.add(sb.reverse().toString());
	
		}
		return res;
	}
	
	/**
	 * helper recursion method that goes deeper if the node has any child.
	 * store the combined string if a complete word is found.
	 * @param res store the subtree
	 * @param prev combined partial string 
	 * @param node current node
	 */
	void subtreehelper (ArrayList <String> res, 
			StringBuilder prev,
			TrieNode node) {
		
		//if the word is completed, add it to res
		if (node.isCompleted()) {
		
			res.add(prev.toString());
		}
	
		//iterating through all of its child
		for (Entry <Character, StringBuilder> e : node.getChildrenString().entrySet()) {
		
				char curkey = e.getKey();//key of the node
				
				StringBuilder cur = new StringBuilder(prev);//create a new buffer to store the new partial key
				cur.append(e.getValue());//append the current value to previous string
				
				//if the child node is valid, go to next level
				 if (node.getChild(curkey) != null) {
						
					 subtreehelper(res, cur, node.getChild(curkey));
				 }
		}
	}
}



