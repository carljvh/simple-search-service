package com.carlhjalmarsson;

import java.util.List;

/**
 * Enforces that each implementing class provides a method for searching for a
 * string in a corpus of data, and returning a list of the contents of a generic
 * type.
 */
public interface Searchable<E> {

    public List<E> search(String s);

}