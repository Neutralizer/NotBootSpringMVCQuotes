package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * POJO quote class - the actual quotes.
 * 
 * @author Neutralizer
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quote {

    private int id;
    private String quote;
    private String author;
    private int rating;
}
