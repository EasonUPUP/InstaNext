package edu.virginia.cs.index.similarities;

import org.apache.lucene.search.similarities.BasicStats;
import org.apache.lucene.search.similarities.LMSimilarity;

public class JelinekMercer extends LMSimilarity {

    private LMSimilarity.DefaultCollectionModel model; // this would be your reference model
    private float queryLength = 0; // will be set at query time automatically

    public JelinekMercer() {
        model = new LMSimilarity.DefaultCollectionModel();
    }

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
    	float lamda = 0.1f; //default_lamda = 0.1
    	float alpha_d = lamda;
    	float Pwd = (float) ((1 - lamda) * termFreq / docLength + lamda * model.computeProbability(stats));
    	
    	return (float) (Math.log10(Pwd / (alpha_d * model.computeProbability(stats) )) + queryLength * Math.log10(alpha_d));
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public String getName() {
        return "Jelinek-Mercer Language Model";
    }

    public void setQueryLength(float length) {
        queryLength = length;
    }
}