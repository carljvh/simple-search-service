package com.carlhjalmarsson;

import java.util.List;
import java.util.Map;

/**
 * Enforces that each implementing class provides a method for ranking the
 * contents of a Map using the size of the corpus of data provided. Intended for
 * classes that are used in objects of type SearchEngine.
 */
public interface RankedRetrievalModel {

    public void rrmodel(Map<String, List<ResultString>> index, int corpus_size);

}