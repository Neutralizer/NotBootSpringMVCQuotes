package com.dao;

import com.model.Quote;
import org.springframework.data.repository.CrudRepository;

public interface QuoteRepository1 extends CrudRepository<Quote,Integer> {
}
