package edu.virginia.cs.index.similarities;

import org.apache.lucene.search.similarities.BasicStats;
import org.apache.lucene.search.similarities.SimilarityBase;

public class PivotedLength extends SimilarityBase {
    /**
     * Returns a score for a single term in the document.
     *
     * @param stats
     *            Provides access to corpus-level statistics
     * @param termFreq
     * @param docLength
     */
	@Override
    protected float score(BasicStats stats, float termFreq, float docLength) {
    	float s = 0.25f; // default_s = 0.75 
    	float cwq = 1.0f; //assume that the query term frequency is always one.
    	float df = stats.getDocFreq();
    	float n_avg = stats.getAvgFieldLength();
    	double k = 0.5;
    	
    	float term1 = (float) (1 + Math.log(1 + Math.log(termFreq))); 
    			// (1 - s + s * docLength / n_avg );
    	float term2 = cwq * (float) Math.log((stats.getNumberOfDocuments() + 1) / df );
    	float term3 = (n_avg + s)/(n_avg + docLength * s);
    	return term1 * term2 * term3;
    }
//    protected float score(BasicStats stats, float termFreq, float docLength) {
//    	float s = 0.75f; // default_s = 0.75 
//    	float cwq = 1.0f; //assume that the query term frequency is always one.
//    	float df = stats.getDocFreq();
//    	float n_avg = stats.getAvgFieldLength();
//    	
//    	float term1 = (float) (1 + Math.log(1 + Math.log(termFreq))) / (1 - s + s * docLength / n_avg );
//    	float term2 = cwq * (float) Math.log((stats.getNumberOfDocuments() + 1) / df );
//    	
//    	return term1 * term2;
//    }

    @Override
    public String toString() {
        return "Pivoted Length Normalization";
    }

}
