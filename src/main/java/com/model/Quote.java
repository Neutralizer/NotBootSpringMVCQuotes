package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * POJO quote class - the actual quotes.
 * 
 * @author Neutralizer
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String quote;
    private String author;
    private int rating;

//    public Quote (String quote, String author, int rating){
//        this.quote = quote;
//        this.author = author;
//        this.rating = rating;
//    }
}
