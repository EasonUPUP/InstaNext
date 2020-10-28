package edu.virginia.cs.index.similarities;

import org.apache.lucene.search.similarities.BasicStats;
import org.apache.lucene.search.similarities.SimilarityBase;

import opennlp.maxent.Main;
import runners.SetPara;

public class OkapiBM25 extends SimilarityBase {
	//public class OkapiBM25 extends SimilarityBase	
	 public static int i = 0;
	
	/**
     * Returns a score for a single term in the document.
     *
     * @param stats
     *            Provides access to corpus-level statistics
     * @param termFreq
     * @param docLength
     * @param k1
     */
	@Override
    protected float score(BasicStats stats, float termFreq, float docLength) {
    	//default value k1=1.5 k2=750 and b=1.0
    	//SetPara set1= new SetPara();
		float k1 = 1.5f;
		float b = 1.1f;
    	//float k1 = 0;
    	float k2 = 750.0f;
    	float df = stats.getDocFreq();
    	float cwq = 1.0f;  //assume that the query term frequency is always one
    	float n_avg = stats.getAvgFieldLength();
    	
    	float term1 = (float) Math.log((stats.getNumberOfDocuments() - df + 0.5) / (df + 0.5)); 
    	float term2 = (float) (k1 + 1) * termFreq / (k1 * (1 - b + b * docLength / n_avg) + termFreq); 
    	float term3 = (float) (k2 + 1) * cwq / (k2 + cwq); 
    	//System.out.println("doc "+ i + " okapi score: " + term1 * term2 * term3);
    	//i++;
    	return term1 * term2 * term3;
    }
    

    @Override
    public String toString() {
        return "Okapi BM25";
    }
//    public float setPara(float k_1) {
//    	return k_1;
//    }
}
