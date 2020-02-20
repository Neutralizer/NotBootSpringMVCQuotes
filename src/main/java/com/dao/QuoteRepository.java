package com.dao;

import com.model.Quote;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface QuoteRepository extends PagingAndSortingRepository<Quote,Integer> {
    List<Quote> findAllQuotes();

    List<Quote> findByQuote(String quote);

}
