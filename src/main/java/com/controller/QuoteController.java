package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.model.Quote;
import com.service.QuoteService;

/**
 * Quote controller with basic CRUD operations.
 *
 * @author Neutralizer
 */

@RestController
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    /**
     * Create quote. Test
     */
    @PostMapping(path = "/quotes" , consumes = "application/json" )
    public ResponseEntity createQuote(@RequestBody Quote quote) {

        quoteService.addQuote(quote);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Get all quotes.
     */
    @GetMapping(path = "/quotes", produces = "application/json")
    public ResponseEntity<List<Quote>> getQuotes() {

        List<Quote> allQuotes = quoteService.getAllQuotes();

        return new ResponseEntity<>(allQuotes, HttpStatus.OK);
    }

    /**
     * Get quote by id.
     */
    @GetMapping(path = "/quotes/{id}")
    public Quote getQuote(@PathVariable int id) {

        Quote quote = quoteService.getQuote(id);

        return quote;

    }

    /**
     * Delete quote by id.
     */
    @DeleteMapping(path = "/quotes/{id}")
    public ResponseEntity deleteQuote(@PathVariable int id) {

        quoteService.remove(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
