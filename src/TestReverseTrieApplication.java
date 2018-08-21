import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;


/**
 * JUnit test class that test the output from the Trie against the expected output
 * @author chengkang xu
 *
 */

class TestReverseTrieApplication {
	
	/**
	 * tell if insertion is succeed or abort because of invalid entry
	 * @param tester testing application
	 * @return whether it succeed
	 */

	boolean insertSucceed (ReverseTrieApplication tester) {
		return tester.getLastInsertSucceed();
	}
	
	/**
	 * check whether rhyming word can not be found
	 * @param in input word
	 * @param tester tester app
	 * @return whether it is found
	 */
	boolean isNull (String in, ReverseTrieApplication tester) {
		return tester.findRhyme(in) == null;
	}
	
	/**
	 * check whether the search is searching from a correct pool
	 * @param in target string
	 * @param tester tester app
	 * @param hs correct pool
	 * @return whether the pool is correct
	 */
	
	boolean hasCorrectPool( String in, ReverseTrieApplication tester, String [] hs) {
		ArrayList <String> res = tester.getRhymeWordPool(in);
		if (res.size() != hs.length) {
			return false;
		}
		for (int i = 0; i < hs.length; i ++) {
			if (res.contains(hs[i])) {
				res.remove(hs[i]);
			}
			else {
				return false;
			}
		}
		return res.size() == 0;
	}
	
	@Test
	void test() {
		System.out.println("rhymelist1");
		//test rhymelist 1
		String [] rhymelist = new String []{"Computing",
				"Polluting",
				"Diluting","Recruiting",
				"Commuting",
				"Fish",
				"Recruiting",
				"Drooping",
				"Banana"};//define input rhyme list
		
		
		ReverseTrieApplication tester = new ReverseTrieApplication (true, rhymelist);//create a new utility class
		assertTrue("Failed handle empty input", isNull("", tester));
		assertTrue("Failed handle null input", isNull(null, tester));
		
		String [] ting = new String [] {"Computing" ,"Diluting", "Polluting", "Commuting","Recruiting"};
		assertTrue("Failed to handle suffix input: ting", hasCorrectPool("ting", tester, ting));
		
		String [] Shooting = new String [] {"Computing" ,"Diluting", "Polluting", "Commuting","Recruiting"};
		assertTrue("Failed to handle common suffix with many words in trie: Shooting", hasCorrectPool("Shooting", tester, Shooting));
		
		String [] Disputing = new String [] {"Computing"};
		assertTrue("Failed to handle common suffix with one word: Disputing", hasCorrectPool("Disputing", tester, Disputing));
		
		String [] Convoluting = new String [] {"Diluting","Polluting"};
		assertTrue("Failed to handle common suffix with many words in trie: Convoluting", hasCorrectPool("Convoluting", tester, Convoluting));
		
		assertTrue("Failed handle input does not match anynode", isNull("Orange", tester));
		
		String[] Banana = new String [] {"Banana"};
		assertTrue("Failed to handle suffix input: ting", hasCorrectPool("Banana", tester, Banana));
		
		String[] kbBanana = new String [] {"Banana"};
		assertTrue("Failed to handle suffix input: ting", hasCorrectPool("kbBanana", tester, kbBanana));

		
		//test rhymelist 2
		System.out.println("=======================");
		System.out.println("rhymelist2");
		String [] rhymelist2 = new String []{"banana", 
				"computing", 
				"fish", 
				"goldfish",
				"ana",
				"commuting",
				"drooping"};//define input rhyme list
		
		
		ReverseTrieApplication tester2 = new ReverseTrieApplication (true, rhymelist2);//create a new utility class

		String [] ping = new String [] {"drooping"};
		assertTrue("Failed to handle suffix input: ping", hasCorrectPool("ping", tester2, ping));
		
		String [] ana = new String [] {"ana", "banana"};
		assertTrue("Failed to handle suffix input: ana", hasCorrectPool("ana", tester2, ana));
		
		String [] fish = new String [] {"fish", "goldfish"};
		assertTrue("Failed to handle suffix input: fish", hasCorrectPool("fish", tester2, fish));
		
		String [] goldfish= new String [] {"goldfish"};
		assertTrue("Failed to handle entire mathcing words: goldfish", hasCorrectPool("goldfish", tester2, goldfish));
		
		
		//test empty rhymelist 
		
		System.out.println("=======================");
		System.out.println("rhymelist3 & 4");
		String [] rhymelist3 = null;
		String [] rhymelist4 = new String [] {};
		ReverseTrieApplication tester3 = new ReverseTrieApplication (true, rhymelist3);
		ReverseTrieApplication tester4 = new ReverseTrieApplication (true, rhymelist4);
		
		assertTrue("Failed handle null insertion", !insertSucceed(tester3));
		assertTrue("Failed handle empty insertion", !insertSucceed(tester4));
	}

}
