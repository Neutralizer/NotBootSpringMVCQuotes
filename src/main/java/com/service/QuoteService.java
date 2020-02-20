package com.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dao.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.QuoteRepo;
import com.model.Quote;


/**
 * 
 * QuoteService class for accessing the dao layer.
 * 
 * @author Neutralizer
 *
 */
@Service
public class QuoteService {

	@Autowired
    private QuoteRepo quoteRepository;

    @Autowired
    private QuoteRepository quoteRepository1;


	/**
	 * Return all quotes from repo.
	 * @return All quotes
	 */
    public List<Quote> getAllQuotes() {
//        return quoteRepository.getAllQuotes();
        List<Quote> all = (List<Quote>) quoteRepository1.findAll();
        return all;
    }

    /**
     * 
     * Retrieves quote by its id. Not zero based.
     * @param id The id of the quote
     * @return The quote
     */
    public Quote getQuote(int id) {
        if (id < 1) throw new IllegalArgumentException("index can't be 0 or negative");
        return quoteRepository.get(id -1);
    }

    /**
     * Adds quote to repo.
     * @param quote The quote to be added
     */
    public void addQuote(Quote quote) {
    	quoteRepository.add(quote);
    }

    /**
     * Removes quote by id.
     * @param id The id of quote to be removed
     */
    public void remove(int id) {
    	quoteRepository.remove(id);
    }

    /**
     * Adds 1 rating to the quote
     * @param id The id of the quote
     */
    public void addRating(int id){
        quoteRepository.addRating(id);
    }

    /**
     * Removes 1 rating from the quote
     * @param id The id of the quote
     */
    public void removeRating(int id){
        quoteRepository.removeRating(id);
    }

}
