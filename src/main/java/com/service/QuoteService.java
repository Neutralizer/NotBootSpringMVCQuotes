package com.service;

import com.dao.QuoteRepository;
import com.model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


/**
 * QuoteService class for business logic.
 *
 * @author Neutralizer
 */
@Service
public class QuoteService {

    private QuoteRepository quoteRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }


    /**
     * Return all quotes from repo.
     *
     * @return All quotes
     */
    public List<Quote> getAllQuotes() {
        return (List<Quote>) quoteRepository.findAll();
    }

    /**
     * Retrieves quote by its id.
     *
     * @param id The id of the quote
     * @return The quote
     */
    public Quote getQuote(int id) {
        Optional<Quote> optional = quoteRepository.findById(id);
        return optional.orElse(null);
    }

    /**
     * Adds quote to repo.
     *
     * @param quote The quote to be added
     */
    public void addQuote(Quote quote) {
        quoteRepository.save(quote);
    }

    /**
     * Removes quote by id.
     *
     * @param id The id of quote to be removed
     */
    public void remove(int id) {
        quoteRepository.deleteById(id);
    }

    /**
     * Adds 1 rating to the quote
     *
     * @param id The id of the quote
     */
    public void addRating(int id) {
        Optional<Quote> optionalQuote = quoteRepository.findById(id);
        if (optionalQuote.isPresent()) {
            Quote quote = optionalQuote.get();
            quote.setRating(quote.getRating() + 1);
            quoteRepository.save(quote);
        } else {
            throw new NoSuchElementException(id + " Quote not present");
        }
    }

    /**
     * Removes 1 rating from the quote.
     *
     * @param id The id of the quote.
     */
    public void removeRating(int id) {
        Optional<Quote> optionalQuote = quoteRepository.findById(id);
        if (optionalQuote.isPresent()) {
            Quote quote = optionalQuote.get();
            quote.setRating(quote.getRating() - 1);
            quoteRepository.save(quote);
        } else {
            throw new NoSuchElementException(id + " Quote not present");
        }
    }

    /**
     * Retrieves all quotes by given author.
     *
     * @param author Author of quotes.
     * @return All quotes by given author.
     */
    public List<Quote> getAllQuotesOfAuthor(String author) {
        return quoteRepository.findByAuthor(author);
    }

    /**
     * Retrieves all quotes with given rating.
     *
     * @param rating Rating of quotes.
     * @return All quotes with given rating.
     */
    public List<Quote> getAllQuotesWithRating(int rating) {
        return quoteRepository.findByRating(rating);
    }

    /**
     * Retrieves all quotes by given author with given rating
     *
     * @param author Author of quotes.
     * @param rating Rating of quotes.
     * @return All quotes by given author with given rating
     */
    public List<Quote> getAllQuotesOfAuthorWithRating(String author, Integer rating) {
        return quoteRepository.findByAuthorAndRating(author,rating);
    }
}
