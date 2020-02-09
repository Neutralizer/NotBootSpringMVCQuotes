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


	
    public List<Quote> getAllQuotes() {
        return quoteRepository.getAllQuotes();
    }

    /**
     * 
     * 
     * @param id
     * @return
     */
    public Quote getQuote(int id) {
        return quoteRepository.get(id);
    }

    public void addQuote(Quote quote) {
    	quoteRepository.add(quote);
    }

    public void remove(int id) {
    	quoteRepository.remove(id);
    }

}
