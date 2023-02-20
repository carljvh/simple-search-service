package com.carlhjalmarsson;

import java.util.List;

/**
 * Enforces that each extending class uses a specific format for their data, as
 * well as a ranked retrieval model. Provides a method for searching for a
 * string in a corpus of data, and returning a list of the contents of type
 * Document. Intended for classes that searches data of type Document.
 */
public abstract class SearchEngine implements Searchable<String> {
    Formattable format;
    RankedRetrievalModel rankingmodel;

    @Override
    public abstract List<String> search(String s);

}