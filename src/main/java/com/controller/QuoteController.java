package com.controller;

import com.model.Quote;
import com.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.StringUtils.isEmpty;

/**
 * Quote controller with basic CRUD operations.
 *
 * @author Neutralizer
 */

@RestController
public class QuoteController {


    private QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    /**
     * Create quote.
     */
    @PostMapping(path = "/quotes", consumes = "application/json")
    public ResponseEntity createQuote(@RequestBody Quote quote) {

        quoteService.addQuote(quote);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Get all quotes.
     */
    @GetMapping(path = "/quotes", produces = "application/json")
    public ResponseEntity<List<Quote>> getQuotes(@RequestParam(required = false) String author,
                                                 @RequestParam(required = false) Integer rating,
                                                 @RequestParam(required = false) String source) {
        List<Quote> allQuotes = null;
        if (!isEmpty(author) && !isEmpty(rating)) {
            allQuotes = quoteService.getAllQuotesOfAuthorWithRating(author, rating);
        }
        if (!isEmpty(author) && isEmpty(rating)) {
            allQuotes = quoteService.getAllQuotesOfAuthor(author);
        }
        if (isEmpty(author) && !isEmpty(rating)) {
            allQuotes = quoteService.getAllQuotesWithRating(rating);
        }
        if (isEmpty(author) && isEmpty(rating)) {
            allQuotes = quoteService.getAllQuotes();
        }

        return new ResponseEntity<>(allQuotes, HttpStatus.OK);


    }


    /**
     * Get quote by id.
     */
    @GetMapping(path = "/quotes/{id}")
    public ResponseEntity<Quote> getQuote(@PathVariable int id) {

        Quote quote = quoteService.getQuote(id);

        return new ResponseEntity<>(quote, HttpStatus.OK);

    }

    /**
     * Delete quote by id.
     */
    @DeleteMapping(path = "/quotes/{id}")
    public ResponseEntity deleteQuote(@PathVariable int id) {

        quoteService.remove(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * Add like to specified quote
     *
     * @param id id of the specified quote
     * @return HttpStatus.OK on success
     */
    @PostMapping(path = "/quotes/{id}/like")
    public ResponseEntity likeQuote(@PathVariable int id) {
        quoteService.addRating(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Add dislike to specified quote
     *
     * @param id id of the specified quote
     * @return HttpStatus.OK on success
     */
    @PostMapping(path = "/quotes/{id}/dislike")
    public ResponseEntity dislikeQuote(@PathVariable int id) {
        quoteService.removeRating(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
