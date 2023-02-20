package com.carlhjalmarsson;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    /**
     * Instantiates a SimpleSearchEngine object by providing a formatter, a ranked
     * retrieval model and data to search.
     * 
     * @param doclist data for the engine to search
     * @return instantiation of a simple search engine
     */
    public static SimpleSearchEngine startEngine(List<String> doclist) {
        StringFormatter formatter = new StringFormatter();
        RankedRetrievalModel tf_idf = new TF_IDF();

        return new SimpleSearchEngine(doclist, formatter, tf_idf);
    }

    public static void main(String[] args) {
        // Take in data
        List<String> documentList = new ArrayList<>();
        /*
         * String doc1 = "the brown fox jumped over the brown dog"; String doc2 =
         * "the lazy brown dog sat in the corner"; String doc3 =
         * "the red fox bit the lazy dog"; documentList.add(doc1);
         * documentList.add(doc2); documentList.add(doc3);
         */
        String st1 = "Hi, my name is carl, carl is my name.";
        String st2 = "Carl and ylva are eating breakfast.";
        documentList.add(st1);
        documentList.add(st2);
        Searchable<String> mySearchEngine = startEngine(documentList);

        Scanner sc = new Scanner(System.in);
        String query = sc.nextLine();
        mySearchEngine.search(query);
        // Format to data
        // Print out
        sc.close();
    }
}