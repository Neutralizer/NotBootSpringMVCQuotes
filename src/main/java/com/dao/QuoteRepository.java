package com.dao;

import com.model.Quote;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface QuoteRepository extends PagingAndSortingRepository<Quote,Integer> {

//    List<Quote> findByQuote(String quote);

}
