package com.carlhjalmarsson;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class implements the Formattable interface in order to format the
 * contents of Strings. The purpose of this class is to simplify the search
 * function of a search engine of type SearchEngine.
 */
public class StringFormatter implements Formattable {
    private String document;

    public StringFormatter() {
        this.document = "";
    }

    public StringFormatter(String doc) {
        this.document = doc;
    }

    /**
     * Splits the string into string elements at spaces. The string elements are
     * then saved to a list.
     * 
     * @return a list of tokenized strings
     */
    public List<String> tokenize() {
        List<String> tokens;
        tokens = new ArrayList<>(Arrays.asList(document.split("\\s")));
        return tokens;
    }

    /**
     * Converts a word to lower case and removes characters which are not part of
     * the alphabet.
     * 
     * @param normWord word to be normalized
     * @return normalized word
     */
    public String normalize(String normWord) {
        String word;
        word = normWord.toLowerCase();
        for (int i = 0; i < word.length(); i++)
            if (word.charAt(i) < 97 || 122 < word.charAt(i))
                word = word.replace(word.charAt(i), (char) 0);
        return word;
    }

    /**
     * Checks if sWord is a stop word by comparing it to a list of stop words.
     * 
     * @param sWord word to check against stop words
     * @return true if sWord is a stop word, else false
     */
    public boolean isStopWord(String stopWord) {
        String[] stop = { "i", "me", "my", "myself", "we", "our", "ours", "ourselves", "you", "yours", "yourself",
                "yourselves", "he", "him", "his", "himself", "she", "her", "hers", "herself", "it", "its", "itself",
                "they", "them", "their", "theirs", "themselves", "what", "which", "who", "whom", "this", "that",
                "these", "those", "am", "is", "are", "was", "were", "be", "been", "being", "have", "has", "had",
                "having", "do", "does", "did", "doing", "a", "an", "the", "and", "but", "if", "or", "because", "as",
                "until", "while", "of", "at", "by", "for", "with", "about", "against", "between", "into", "through",
                "during", "before", "after", "above", "below", "to", "from", "up", "down", "in", "out", "on", "off",
                "over", "under", "again", "further", "then", "once", "here", "there", "when", "where", "why", "how",
                "all", "any", "both", "each", "few", "more", "most", "other", "some", "such", "no", "nor", "not",
                "only", "own", "same", "so", "than", "too", "very", "s", "t", "can", "will", "just", "don", "should",
                "now" };
        List<String> stopWords = Arrays.asList(stop);
        return stopWords.contains(stopWord);
    }

    // “Porter’s Stemming Algorithm”
    // Was going to implement a stemmer, but the task was a bit too demanding for
    // the time constraint.
    public String removeStem(String rootWord) {
        return "";
    }

    /**
     * Formats a String using the methods in the class: First creates tokens out of
     * String content, second normalizes each word in turn, third checks if the
     * given word is a stop word.
     * 
     * @param doc String to be formatted
     * @return a list of strings containing the formatted content of the String
     */
    public List<String> formatize(String doc) {
        this.document = doc;
        List<String> formattedDoc = new ArrayList<>(0);
        List<String> stringDocument = tokenize();
        Iterator<String> tokenIt = stringDocument.iterator();
        String word = "";
        int i = 0;
        while (tokenIt.hasNext()) {
            String n = tokenIt.next();
            word = normalize(n);
            if (isStopWord(word))
                continue;
            else {
                formattedDoc.add(word);
            }
            i++;
        }
        return formattedDoc;
    }
}