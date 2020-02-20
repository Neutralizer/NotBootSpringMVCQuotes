package com.service;

import com.dao.QuoteRepo;
import com.dao.QuoteRepository;
import com.model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


/**
 * QuoteService class for accessing the dao layer.
 *
 * @author Neutralizer
 */
@Service
public class QuoteService {

    @Autowired
    private QuoteRepo quoteRepository;

    @Autowired
    private QuoteRepository quoteRepository1;


    /**
     * Return all quotes from repo.
     *
     * @return All quotes
     */
    public List<Quote> getAllQuotes() {
//        return quoteRepository.getAllQuotes();
        return (List<Quote>) quoteRepository1.findAll();
    }

    /**
     * Retrieves quote by its id. Not zero based.
     *
     * @param id The id of the quote
     * @return The quote
     */
    public Quote getQuote(int id) {
//        if (id < 1) throw new IllegalArgumentException("index can't be 0 or negative");
//        return quoteRepository.get(id -1);
        Optional<Quote> optional = quoteRepository1.findById(id);
        return optional.orElse(new Quote()); // TODO null or empty ?
    }

    /**
     * Adds quote to repo.
     *
     * @param quote The quote to be added
     */
    public void addQuote(Quote quote) {
//    	quoteRepository.add(quote);
        quoteRepository1.save(quote);
    }

    /**
     * Removes quote by id.
     *
     * @param id The id of quote to be removed
     */
    public void remove(int id) {
//    	quoteRepository.remove(id);
        quoteRepository1.deleteById(id);
    }

    /**
     * Adds 1 rating to the quote
     *
     * @param id The id of the quote
     */
    public void addRating(int id) {
//        quoteRepository.addRating(id);
        Optional<Quote> optionalQuote = quoteRepository1.findById(id);
        if (optionalQuote.isPresent()) {
            Quote quote = optionalQuote.get();
            quote.setRating(quote.getRating() + 1);
            quoteRepository1.save(quote);
        } else {
            throw new NoSuchElementException(id + " Quote not present");
        }
    }

    /**
     * Removes 1 rating from the quote
     *
     * @param id The id of the quote
     */
    public void removeRating(int id) {
//        quoteRepository.removeRating(id);
        Optional<Quote> optionalQuote = quoteRepository1.findById(id);
        if (optionalQuote.isPresent()) {
            Quote quote = optionalQuote.get();
            quote.setRating(quote.getRating() - 1);
            quoteRepository1.save(quote);
        } else {
            throw new NoSuchElementException(id + " Quote not present");
        }
    }

}
