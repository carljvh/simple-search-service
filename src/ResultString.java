package com.carlhjalmarsson;

/**
 * Help class to store information of a Document in an object of type
 * SearchEngine. Fields: document - Document to be rated, ID - ID of document,
 * terms - The number of times a specific term appears in the document
 */
public class ResultString {
    String document;
    int ID;
    int terms;
    double tf_idf;

    public ResultString(String doc, int docID, int freq) {
        this.ID = docID;
        this.document = doc;
        this.terms = freq;
    }

    /**
     * Getter for variable document.
     * 
     * @return document
     */
    public String getString() {
        return document;
    }

    /**
     * Getter for variable terms.
     * 
     * @return terms
     */
    public int getTerms() {
        return terms;
    }

    /**
     * Getter for the number of words in the document.
     * 
     * @return length of document
     */
    public int getWords() {
        return document.length();
    }

    /**
     * Getter for variable ID
     * 
     * @return document ID
     */
    public int getDocID() {
        return this.ID;
    }

    /**
     * Setter for variable tf_idf, representing the ranking of the document.
     * 
     * @param ranking the ranking of the document according to a ranked retrieval
     *                model
     */
    public void setRanking(double ranking) {
        this.tf_idf = ranking;
    }

    /**
     * Getter for variable tf_idf, representing the ranking of the document.
     * 
     * @return the ranking of the document according to a ranked retrieval model
     */
    public double getRanking() {
        return this.tf_idf;
    }

    /**
     * Overriden hashCode to be compatible with equals
     * 
     * @return hashCode of object
     */
    public int hashCode() {
        return 1 + 23 * (int) Math.round(tf_idf) * 1000;
    }

    /**
     * Compares two objects, used to implement a comparator for this object type
     * 
     * @param obj, object to be compared with this object instance
     * @return true if objects are equal, else false
     */
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ResultString))
            return false;

        ResultString doc = (ResultString) obj;
        if (this.tf_idf == doc.tf_idf)
            return true;
        else
            return false;
    }
}