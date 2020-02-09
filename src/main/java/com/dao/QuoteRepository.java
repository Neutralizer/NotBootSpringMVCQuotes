package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.model.Quote;

/**
 * Temp repository class to store quotes.
 * 
 * @author Neutralizer
 *
 */
@Repository
public class QuoteRepository {

	private List<Quote> quotes = new ArrayList<Quote>();

	public QuoteRepository() {
		quotes.add(new Quote(1, "It's over 9000!!!"));
		quotes.add(new Quote(2, "I will defend my nakama!"));
		quotes.add(new Quote(3, "I smell meat!"));
		quotes.add(new Quote(4, "Omae wa mou shindeiru!"));
		quotes.add(new Quote(5, "I will be the pirate king!"));
	}

	/**
	 * Retrieve all quotes.
	 * 
	 * @return All quotes
	 */
	public List<Quote> getAllQuotes() {
		return quotes;
	}

	/**
	 * Get single quote when provided with its id. 
	 * Not zero based. 
	 * @param id
	 *            The id of the quote
	 * @return
	 */
	public Quote get(int id) {
		if (id < 1) throw new IllegalArgumentException("index can't be 0 or negative");
		return quotes.get(id - 1 );
	}

	/**
	 * Adds quote to repository.
	 * 
	 * @param quote
	 *            The quote to be added
	 */
	public void add(Quote quote) {
		quotes.add(quote);
	}

	/**
	 * Removes the quote with the given id.
	 * 
	 * @param id
	 *            The id of the quote to be removed
	 */
	public void remove(int id) {
		quotes.remove(id);
	}

}
