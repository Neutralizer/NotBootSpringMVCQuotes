package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.hamcrest.core.StringContains.containsString;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.dao.QuoteRepository;
import com.model.Quote;
import com.service.QuoteService;

class TestService {

	private QuoteService quoteService = new QuoteService();
	private QuoteRepository quoteRepository = new QuoteRepository();

	@BeforeEach
	public void setUp() throws Exception {
		ReflectionTestUtils.setField(quoteService, "quoteRepository", quoteRepository);
	}

	@Test
	public void getAllQuotesNotNull() {
		List<Quote> allQuotes = quoteService.getAllQuotes();
		assertNotNull(allQuotes);
	}

	@Test
	public void getAllQuotesLength() {
		List<Quote> allQuotes = quoteService.getAllQuotes();
		assertEquals(allQuotes.size(), 5);
	}

	@Test
	public void getAllQuotesFirstQuote() {
		List<Quote> allQuotes = quoteService.getAllQuotes();
		assertEquals(allQuotes.get(0), new Quote(1, "It's over 9000!!!","Vegeta",0));
	}

	@Test
	public void getQuoteNotNull() {
		Quote quote = quoteService.getQuote(2);
		assertNotNull(quote);
	}
	
	@Test
	public void getQuoteCheckString() {
		Quote quote = quoteService.getQuote(3);
		org.hamcrest.MatcherAssert.assertThat(quote.getQuote(), containsString("meat"));
	}
	
	@Test
	public void getQuoteCheckId() {
		Quote quote = quoteService.getQuote(4);
		assertEquals(quote.getId(), 4);
	}
	
	@Test
	public void removeQuote() {
		quoteService.remove(4);
		List<Quote> allQuotes = quoteService.getAllQuotes();
		assertEquals(allQuotes.size(), 4);
	}
	
	@Test
	public void addQuote() {
		Quote quote = new Quote(6, "yare yare daze","Me",0);
		quoteService.addQuote(quote);
		List<Quote> allQuotes = quoteService.getAllQuotes();
		assertEquals(allQuotes.size(), 6);
	}

	@Test
	public void addQuoteAndCheckString() {
		Quote quote = new Quote(6, "yare yare daze","Me",0);
		quoteService.addQuote(quote);
		Quote quote2 = quoteService.getQuote(6);
		org.hamcrest.MatcherAssert.assertThat(quote2.getQuote(), containsString("daze"));
	}

	@Test
	public void addLikeToQuote() {
		Quote quote = new Quote(6, "oh no!","Me",0);
		quoteService.addQuote(quote);
		quoteService.addRating(6);
		assertEquals(quoteService.getQuote(6).getRating(), 1);
	}

	@Test
	public void addDislikeToQuote() {
		Quote quote = new Quote(6, "oh no!","Me",0);
		quoteService.addQuote(quote);
		quoteService.removeRating(6);
		assertEquals(quoteService.getQuote(6).getRating(), -1);
	}

	@Test
	public void addLikeAndDislikeToQuote() {
		Quote quote = new Quote(6, "oh no!","Me",0);
		quoteService.addQuote(quote);
		quoteService.removeRating(6);
		quoteService.addRating(6);
		assertEquals(quoteService.getQuote(6).getRating(), 0);
	}



}
