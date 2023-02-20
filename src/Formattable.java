package com.carlhjalmarsson;

import java.util.List;

/**
 * Enforces that each implementing class use the given methods normalize,
 * isStopWord and removeStem. Intended for classes that formats strings.
 */
public interface Formattable {

    public List<String> tokenize();

    public String normalize(String normWord);

    public boolean isStopWord(String stopWord);

    public String removeStem(String rootWord);

}