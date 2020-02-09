package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.QuoteRepository;
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
    QuoteRepository quoteRepository;


	/**
	 * Return all quotes from repo.
	 * @return All quotes
	 */
    public List<Quote> getAllQuotes() {
        return quoteRepository.getAllQuotes();
    }

    /**
     * 
     * Retrieves quote by its id. Not zero based.
     * 
<<<<<<< HEAD
     * @param id The id of the quote
=======
     * @param id The id of the quote.
>>>>>>> branch 'commentsUpdateBranch' of https://github.com/Neutralizer/QuoteManipulator.git
     * @return
     */
    public Quote getQuote(int id) {
        return quoteRepository.get(id);
    }

    /**
     * Adds quote to repo.
<<<<<<< HEAD
     * @param quote The quote to be added
=======
     * @param quote The quote to be added.
>>>>>>> branch 'commentsUpdateBranch' of https://github.com/Neutralizer/QuoteManipulator.git
     */
    public void addQuote(Quote quote) {
    	quoteRepository.add(quote);
    }

    /**
     * Removes quote by id.
<<<<<<< HEAD
     * @param id The id of quote to be removed
=======
     * @param id The id of quote to be removed.
>>>>>>> branch 'commentsUpdateBranch' of https://github.com/Neutralizer/QuoteManipulator.git
     */
    public void remove(int id) {
    	quoteRepository.remove(id);
    }

}
