package edu.virginia.cs.index.similarities;

import org.apache.lucene.search.similarities.BasicStats;
import org.apache.lucene.search.similarities.LMSimilarity;

public class DirichletPrior extends LMSimilarity {

    private LMSimilarity.DefaultCollectionModel model; // this would be your reference model
    private float queryLength = 0; // will be set at query time automatically

    public DirichletPrior() {
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
    	float mu = 1500.0f; //default_mu is 2500
    	float alpha_d = mu / (mu + docLength);
    	float Pwd = (float) (termFreq + mu * model.computeProbability(stats)) / (docLength + mu);
        return (float) (Math.log10(Pwd / (alpha_d * model.computeProbability(stats) )) + queryLength * Math.log10(alpha_d));
     }

    @Override
    public String getName() {
        return "Dirichlet Prior";
    }

    @Override
    public String toString() {
        return getName();
    }

    public void setQueryLength(float length) {
        queryLength = length;
    }
}
