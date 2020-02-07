package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.model.Quote;

@Service
public class QuoteService {

    private List<Quote> quotes = new ArrayList<Quote>();

    public QuoteService() {
        super();
        quotes.add(new Quote(1, "It's over 9000!!!"));
        quotes.add(new Quote(2, "I will defend my nakama!"));
        quotes.add(new Quote(3, "I smell meat!"));
        quotes.add(new Quote(4, "Omae wa mou shindeiru!"));
        quotes.add(new Quote(5, "I will be the pirate king!"));

    }

    public List<Quote> getAllQuotes() {
        return quotes;
    }

    public Quote getQuote(int id) {
        return quotes.get(id);
    }

    public void addQuote(Quote quote) {
        quotes.add(quote);
    }

    public void remove(int id) {
        quotes.remove(id);
    }

}
