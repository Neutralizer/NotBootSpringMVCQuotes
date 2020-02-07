package test.Quotes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.model.Quote;
import com.service.QuoteService;

class TestController {

	@Autowired
	private QuoteService quoteService;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void getAllQuotesLength() {
		List<Quote> allQuotes = quoteService.getAllQuotes();
		assertEquals(allQuotes.size(), 5);

	}

	@Test
	void getAllQuotesNotNull() {
		List<Quote> allQuotes = quoteService.getAllQuotes();
		assertNotNull(allQuotes);
	}

	@Test
	void getAllQuotesFirstQuote() {
		List<Quote> allQuotes = quoteService.getAllQuotes();
		assertEquals(allQuotes.get(1), new Quote(1, "It's over 9000!!!"));
	}

	@Test
	void getQuoteNotNull() {
		Quote quote = quoteService.getQuote(2);
		assertNotNull(quote);
	}

}
