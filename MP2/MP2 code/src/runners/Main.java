package runners;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import edu.virginia.cs.evaluator.Evaluate;
import edu.virginia.cs.index.Indexer;
import edu.virginia.cs.index.ResultDoc;
import edu.virginia.cs.index.SearchResult;
import edu.virginia.cs.index.Searcher;
import org.apache.commons.math3.stat.inference.TTest;
import org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest;

public class Main {
	//please keep those constants 
    final static String _dataset = "npl";
    final static String _indexPath = "lucene-npl-index";
    final static String _prefix = "data/";
    final static String _file = "npl.txt";
    final static String _judgment = "npl-judgements.txt";
    
    /**
     * Feel free to modify this function, if you want different display!
     *
     * @throws IOException
     */
    private static void interactiveSearch(String method) throws IOException {
        Searcher searcher = new Searcher(_prefix + _indexPath);
        Evaluate.setSimilarity(searcher, method);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Type text to search, blank to quit.");
        System.out.print("> ");
        String input;
        
        
        while ((input = br.readLine()) != null && !input.equals("")) {
        	long currentTime = System.currentTimeMillis();
            SearchResult result = searcher.search(input);
            ArrayList<ResultDoc> results = result.getDocs();
            int rank = 1;
            if (results.size() == 0)
                System.out.println("No results found!");
            for (ResultDoc rdoc : results) {
                System.out.println("\n------------------------------------------------------");
                System.out.println(rank + ". " + rdoc.title());
                System.out.println("------------------------------------------------------");
                System.out.println(result.getSnippet(rdoc)
                        .replaceAll("\n", " "));
                ++rank;
            }
            System.out.print("> ");
            long timeElapsed = System.currentTimeMillis() - currentTime;
            System.out.format("Search in %.3f seconds\n", timeElapsed/1000.0);
        }
    }
    
////This enables you to interact with the program in command line
//  public static void main(String[] args) throws IOException {
//      if (args.length == 1 && args[0].equalsIgnoreCase("--index"))
//          Indexer.index(_indexPath, _prefix, _file);
//      else if (args.length >= 1 && args[0].equalsIgnoreCase("--search"))
//      {
//          String method = null;
//          if (args.length == 2)
//              method = args[1];
//          interactiveSearch(method);
//      }
//      else
//      {
//          System.out.println("Usage: --index to index or --search to search an index");
//          System.out.println("If using \"--search\",");
//          printUsage();
//      }
//  }
//    public double tTest(final double mu, final double[] sample)
//            throws NullArgumentException, NumberIsTooSmallException,
//            MaxCountExceededException {
//
//            checkSampleData(sample);
//            // No try-catch or advertised exception because args have just been checked
//            return tTest(StatUtils.mean(sample), mu, StatUtils.variance(sample),
//                         sample.length);
//
//        }
    
    
    
    
		////This makes it easier for you to run the evaluation
		public static void main(String[] args) throws IOException {
		//To crate the index
		//NOTE: you need to create the index once, and you cannot call this function twice without removing the existing index files
//		long currentTime = System.currentTimeMillis();
//		Indexer.index(_prefix + _indexPath, _prefix, _file);
//		long timeElapsed = System.currentTimeMillis() - currentTime;
//        System.out.format("Indexing in %.3f seconds\n", timeElapsed/1000.0);
//		
		//Main.interactiveSearch("--ok");
			
        //Interactive searching function with your selected ranker   "--tfidf" or "--ok"
		//NOTE: you have to create the index before searching!
		Evaluate ev = new Evaluate();
		ev.evaluate("--ok", _prefix + _indexPath, _prefix + _judgment);
		//ev.evaluate_tfidf("--tfidf", _prefix + _indexPath, _prefix + _judgment);
		
		
		
		//double p = t.tTest(ev.ok_MAP, ev.tfidf_MAP);
	    //System.out.println("t-Test: p=" + p);
		
	}

}
