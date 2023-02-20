package com.carlhjalmarsson;

import java.lang.Math;
import java.util.Map;
import java.util.List;
import java.util.Iterator;

public class TF_IDF implements RankedRetrievalModel {

    /**
     * Calculates the term frequency by dividing the number of times a specific term
     * occurs in the document with total number of words in the document.
     * 
     * @param words_in_doc number of words in document
     * @param terms_in_doc number of times a specific term occurs in the document
     * @return term frequency
     */
    // return term frequency:
    private double tf(int words_in_doc, int terms_in_doc) {
        return (double) terms_in_doc / words_in_doc;
    }

    /**
     * Calculates the inverse document frequency.
     * 
     * @param docs_in_corpus number of documents in the corpus of data
     * @param docs_with_term number of documents in which a specific term occurs
     * @return double
     */
    private double idf(int docs_in_corpus, int docs_with_term) {
        return Math.log10((double) docs_in_corpus / (docs_with_term + 1));
    }

    /**
     * @param dic number of Strings in the corpus of data
     * @param dwt number of Strings in which a specific term occurs
     * @param wid number of words in String
     * @param tid number of times a specific term occurs in the String
     * @return the tf-idf score of a word in a String in relation to the corpus
     */
    public double tf_idf(int dic, int dwt, int wid, int tid) {
        return (double) tf(wid, tid) * idf(dic, dwt);
    }

    /**
     * Assigns a tf-idf ranking to each posting in the inverted index
     * 
     * @param unranked    the unranked index
     * @param corpus_size the size of the corpus of data
     */
    public void rrmodel(Map<String, List<ResultString>> unranked, int corpus_size) {
        int docs_in_corpus, docs_with_term, words_in_doc, terms_in_doc;
        docs_in_corpus = corpus_size;
        Iterator<ResultString> it;
        List<ResultString> postings;
        ResultString rdoc;

        for (Map.Entry<String, List<ResultString>> entry : unranked.entrySet()) {
            postings = entry.getValue();
            it = postings.iterator();
            while (it.hasNext()) {
                rdoc = it.next();
                docs_with_term = postings.size();
                words_in_doc = rdoc.getWords();
                terms_in_doc = rdoc.getTerms();
                double ranking = tf_idf(docs_in_corpus, docs_with_term, words_in_doc, terms_in_doc);
                rdoc.setRanking(ranking);
            }
        }
    }
}