package com.controller;

import com.model.Quote;
import com.service.QuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.springframework.util.StringUtils.isEmpty;
import static org.springframework.util.StringUtils.sortStringArray;

/**
 * Quote controller with basic CRUD operations.
 *
 * @author Neutralizer
 */

@RestController
public class QuoteController {

    private static final Logger LOG = LoggerFactory.getLogger(QuoteController.class.getName());


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
                                                 @RequestParam(required = false) String source,
                                                 @RequestParam(required = false) Integer rating) {
        List<Quote> allQuotes = null;
        if (!isEmpty(author) && !isEmpty(rating) && !isEmpty(source)) {
            allQuotes = quoteService.getAllQuotesOfAuthorWithRatingFromSource(author, source, rating);
        }
        if (!isEmpty(author) && !isEmpty(rating) && isEmpty(source)) {
            allQuotes = quoteService.getAllQuotesOfAuthorWithRating(author, rating);
        }
        if (!isEmpty(author) && isEmpty(rating) && !isEmpty(source)) {
            allQuotes = quoteService.getAllQuotesOfAuthorFromSource(author, source);
        }
        if (!isEmpty(author) && isEmpty(rating) && isEmpty(source)) {
            allQuotes = quoteService.getAllQuotesOfAuthor(author);
        }
        if (isEmpty(author) && !isEmpty(rating) && !isEmpty(source)) {
            allQuotes = quoteService.getAllQuotesFromSourceWithRating(source, rating);
        }
        if (isEmpty(author) && !isEmpty(rating) && isEmpty(source)) {
            allQuotes = quoteService.getAllQuotesWithRating(rating);
        }
        if (isEmpty(author) && isEmpty(rating) && !isEmpty(source)) {
            allQuotes = quoteService.getAllQuotesFromSource(source);
        }
        if (isEmpty(author) && isEmpty(rating) && isEmpty(source)) {
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

    @GetMapping(path = "/elk")
    public ResponseEntity generateLog() {
        String response = "Generating log " + new Date();
        LOG.info(response);
        LOG.debug(response);
        LOG.error(response);
        System.out.println("ERROR TEST ERROR");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping(path = "/exception")
    public ResponseEntity error() {
        String errorResponse = "";
        System.out.println("errorResponse TEST errorResponse");
        try {
            throw new Exception("Test exception");
        } catch (Exception e) {
            e.printStackTrace();

            LOG.error(String.valueOf(e));
            errorResponse = e.toString();
        }
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
