package com.carlhjalmarsson;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class extends the searchEngine class in order to search data of type
 * String. To search the data, it implements an inverted index using tf_idf as a
 * ranked retrieval model and a format given by the type StringFormatter.
 * 
 * The purpose of this class is to do a simple search a corpus of Strings of
 * type String, using a single word as data, and returning a list of Strings
 * ranked in order of relevance.
 */
public class SimpleSearchEngine extends SearchEngine {
    private final List<String> corpus;
    private final StringFormatter format;
    private final RankedRetrievalModel rankingModel;
    private HashMap<String, List<ResultString>> index;

    public SimpleSearchEngine(List<String> doclist, StringFormatter myformat, RankedRetrievalModel mymodel) {
        this.corpus = doclist;
        this.format = myformat;
        this.rankingModel = mymodel;
        buildIndex();

    }

    /**
     * Counts how many times a term (single word) appears in a list of words.
     * 
     * @param word  term to count.
     * @param words list to search for word
     * @return the number of times the term appeared
     */
    private int count(String word, List<String> words) {
        List<String> doc = new ArrayList<>(words);
        int no_terms = 0;
        while (doc.remove(word)) {
            no_terms++;

        }
        return no_terms;
    }

    /**
     * Adds an entry to the index, expanding its 'dictionary'.
     * 
     * @param word, key to add to the index
     */
    private void addToDictionary(String word) {
        index.put(word, new ArrayList<ResultString>());
    }

    /**
     * Finds the word in the index and adds a posting (String in which the word
     * occurs) to the index.
     * 
     * @param word, index key
     * @param doc,  posting (value element) to add to value
     */
    private void addPosting(String word, ResultString doc) {
        index.get(word).add(doc);
    }

    /**
     * If the index does not contain the word (key), adds an entire entry to the
     * index. If the value does not contain the posting (value element), adds the
     * posting to the value.
     * 
     * @param word, index key
     * @param doc,  posting (value element) to add to value
     */
    private void addToIndex(String word, ResultString doc) {
        if (!index.containsKey(word)) {
            addToDictionary(word);
            System.out.println(index.keySet());
            System.out.println(index.containsKey("carl"));
        }
        if (!index.get(word).contains(doc)) {
            addPosting(word, doc);
        }
    }

    /**
     * Sorts the index according to the ranked retrieval model, using a comparator
     * of type StringComparator.
     * 
     * @param unsorted, the unsorted index
     */
    private void sort(Map<String, List<ResultString>> unsorted) {
        // sort all Strings in the index;
        ResultStringComparator c = new ResultStringComparator();
        for (Map.Entry<String, List<ResultString>> entry : unsorted.entrySet()) {
            entry.getValue().sort(c);
        }
    }

    /**
     * Builds the index using the above described methods. First iterates through
     * the corpus of data, formatting the content using the StringFormatter class
     * and attributing information to documents using the ResultString class. The
     * method then adds the string documents (value elements) to the index with
     * words (keys). Here, the values correspond to postings. After, it attributes a
     * ranking to the elements within the values using a class of type
     * RankedRetrievalModel. Lastly, it sorts the elements within the values using
     * this ranking.
     */
    public void buildIndex() {
        index = new HashMap<>();
        int docID = 0;
        Iterator<String> docIt = corpus.iterator();
        String doc;
        List<String> words;
        ResultString resultDoc;
        while (docIt.hasNext()) {
            docID++;
            doc = docIt.next();
            words = format.formatize(doc);
            int terms = 0;
            for (String word : words) {
                terms = count(word, words);
                resultDoc = new ResultString(doc, docID, terms);
                addToIndex(word, resultDoc);
            }
        }
        // System.out.println(index.keySet());
        rankingModel.rrmodel(index, corpus.size());
        sort(index);
    }

    /**
     * Searches the corpus of data for hits on the input argument and returns a list
     * of Strings ranked according to a ranked retrieval model.
     * 
     * First formats the input argument. If input is a stop word or the list is
     * empty, prints "No results found" and returns an empty list.
     * 
     * @catch Exception, prints and error message along with the stack trace, then
     *        exits the program if an error occurs.
     * @param query, the input argument, aka. the search term
     * @return list of Strings ordered according to the ranked retrieval model
     */
    public List<String> search(String query) {

        List<String> hits = new ArrayList<>();
        List<ResultString> postings;
        String formattedQuery = format.normalize(query);
        postings = index.get(formattedQuery);

        if (format.isStopWord(query) || postings == null) {
            System.out.println("No results found.");
            return new ArrayList<String>();
        } else {
            try {
                Iterator<ResultString> it = postings.iterator();
                while (it.hasNext())
                    hits.add(it.next().getString());
            } catch (Exception e) {
                System.out.println("Error - Unable to process query." + " " + e.getMessage());
                e.printStackTrace();
                System.exit(0);
            }
        }
        System.out.print("Hits in document: ");
        for (ResultString doc : postings)
            System.out.print("[" + doc.getDocID() + "]" + " ");
        return hits;
    }
}