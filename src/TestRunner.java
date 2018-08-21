

/**
 * tester class that contains the main method
 * @author chengkang xu
 */
public class TestRunner {
	
	public static void test (String in, ReverseTrieApplication tester) {
		System.out.println(tester.findRhyme(in)) ;
		System.out.println();
	}
	
	public static void main (String [] args) {
		
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
		tester.printTrie();
		
		test("", tester);
		test(null, tester);
		test("ting",tester);
		test("shooting", tester);
		test("Disputing", tester);
		test("Convoluting", tester);
		test("Orange", tester);
		test("Banana", tester);
		test("kbBanana", tester);
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
		tester2.printTrie();
		
		test("ana", tester2);
		test("goldfish", tester2);
		test("fish", tester2);
		test("ping", tester2);
		
		//test empty rhymelist 
		
		System.out.println("=======================");
		System.out.println("rhymelist3 & 4");
		String [] rhymelist3 = null;
		String [] rhymelist4 = new String [] {};
		ReverseTrieApplication tester3 = new ReverseTrieApplication (true, rhymelist3);
		ReverseTrieApplication tester4 = new ReverseTrieApplication (true, rhymelist4);
		
	}
}
