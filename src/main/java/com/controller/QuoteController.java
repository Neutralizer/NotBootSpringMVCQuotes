package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.model.Quote;
import com.service.QuoteService;

/**
 * 
 * get method for /quotes - return all quotes get method for /quotes/{id} -
 * return a specific quote with specific id controller with post method for
 * create quote
 *
 * read data from a collection make data layer that works with the collection
 * service layer that manipulates the data controller that communicates with the
 * outside world through REST
 * 
 * @author Neutralizer
 *
 */

@Controller
@ResponseBody
public class QuoteController {

	@Autowired
	QuoteService quoteService;

	@GetMapping(path = "/quotes", produces = "application/json")
	public List<Quote> getQuotes() {

		List<Quote> allQuotes = quoteService.getAllQuotes();

		return allQuotes;
	}

	@GetMapping(path = "/index")
	public ModelAndView getIndex() {

		ModelAndView modelAndView = new ModelAndView("index");

		modelAndView.addObject("quote", "ooooooooooooooo");

		return modelAndView;
	}
	
	@GetMapping(path = "/")
	public ModelAndView getP() {

		ModelAndView modelAndView = new ModelAndView("index", "quote", "start");

		return  modelAndView;
	}

	@GetMapping(path = "/quotes/{id}") 
	public Quote getQuote(@PathVariable int id, Model model) {

		Quote quote = quoteService.getQuote(id);

		return quote;

	}

	@PostMapping(path = "/quotes", consumes = "application/json")
	public void createQuote(@RequestBody Quote quote) {

		quoteService.addQuote(quote);

	}

}
