package com.carlhjalmarsson;

import java.util.Comparator;

public class StringComparator implements Comparator<ResultString> {

    /**
     * Standard implemaentation of the Comparator interface. Compares the ranking of
     * two resultStrings. Returns -1 if the first parameter is less than the second,
     * 0 if the rankings are equal, else 1.
     * 
     * @param doc1 first String to compare
     * @param doc2 second String to compare
     * @return standard output according to the natural ordering of elements
     */
    @Override
    // Jämför två olika bud och returnerar -1 ifall den första parametern är mindre
    // än den sista, 0 ifall buden är identiska, annars 1.
    // O(1) complexity.
    public int compare(ResultString doc1, ResultString doc2) {
        double ranking1, ranking2;
        ranking1 = doc1.getRanking();
        ranking2 = doc2.getRanking();
        if (ranking1 > ranking2)
            return -1;
        else if (doc1.equals(doc2))
            return 0;
        else
            return 1;
    }
}