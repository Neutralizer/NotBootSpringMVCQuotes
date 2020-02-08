package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.dao.QuoteRepository;
import com.model.Quote;
import com.service.QuoteService;

@RunWith(SpringJUnit4ClassRunner.class)
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
		assertEquals(allQuotes.get(0), new Quote(1, "It's over 9000!!!"));
	}

	@Test
	public void getQuoteNotNull() {
		Quote quote = quoteService.getQuote(2);
		assertNotNull(quote);
	}

}
