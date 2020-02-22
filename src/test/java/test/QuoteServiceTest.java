package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.SpringBootApplicationMain;
import com.dao.QuoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.model.Quote;
import com.service.QuoteService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApplicationMain.class)
class QuoteServiceTest {

	@TestConfiguration
	static class QuoteServiceTestContextConfiguration {

		@Bean
		public QuoteService quoteService() {
			return new QuoteService();
		}
	}

	@Autowired
	private QuoteService quoteService;

	@MockBean
	private QuoteRepository quoteRepository;

	@BeforeEach
	public void setUp() {
	}

	@Test
	public void getAllQuotesNotNull() {
		List<Quote> allQuotes = new ArrayList<>();
		allQuotes.add(new Quote(1, "It's over 9000!!!", "Vegeta", 0));
		allQuotes.add(new Quote(2, "I will defend my nakama!", "Luffy", 0));

		when(quoteRepository.findAll()).thenReturn(allQuotes);

		List<Quote> found = quoteService.getAllQuotes();
		assertNotNull(found);
	}

	@Test
	public void getAllQuotesLength() {
		List<Quote> allQuotes = new ArrayList<>();
		allQuotes.add(new Quote(1, "It's over 9000!!!", "Vegeta", 0));
		allQuotes.add(new Quote(2, "I will defend my nakama!", "Luffy", 0));

		when(quoteRepository.findAll()).thenReturn(allQuotes);

		List<Quote> found = quoteService.getAllQuotes();
		assertEquals(found.size(), 2);
	}

	@Test
	public void getAllQuotesFirstQuote() {
		List<Quote> allQuotes = new ArrayList<>();
		allQuotes.add(new Quote(1, "It's over 9000!!!", "Vegeta", 0));
		allQuotes.add(new Quote(2, "I will defend my nakama!", "Luffy", 0));

		when(quoteRepository.findAll()).thenReturn(allQuotes);
		List<Quote> found = quoteService.getAllQuotes();
		assertEquals(found.get(0), new Quote(1, "It's over 9000!!!","Vegeta",0));
	}

	@Test
	public void getQuoteNotNull() {
		Quote quote = new Quote(2, "I will defend my nakama!", "Luffy", 0);

		when(quoteRepository.findById(2)).thenReturn(java.util.Optional.of(quote));

		Quote found = quoteService.getQuote(2);
		assertNotNull(found);
	}
	
	@Test
	public void getQuoteCheckString() {
		Quote quote = new Quote(2, "I will defend my nakama!", "Luffy", 0);

		when(quoteRepository.findById(2)).thenReturn(java.util.Optional.of(quote));

		Quote found = quoteService.getQuote(2);
		org.hamcrest.MatcherAssert.assertThat(found.getQuote(), containsString("defend"));
	}
	
	@Test
	public void getQuoteCheckId() {
		Quote quote = new Quote(2, "I will defend my nakama!", "Luffy", 0);

		when(quoteRepository.findById(2)).thenReturn(java.util.Optional.of(quote));

		Quote found = quoteService.getQuote(2);
		assertEquals(found.getId(), 2);
	}
	
	@Test
	public void removeQuote() {
		quoteService.remove(2);

		verify(quoteRepository).deleteById(2);
	}
	
	@Test
	public void addQuote() {
		Quote quote = new Quote(6, "yare yare daze","Me",0);

		quoteService.addQuote(quote);
		verify(quoteRepository).save(quote);
	}

	@Test
	public void addLikeToQuote() {
		Quote quote = new Quote(6, "oh no!","Me",0);

		when(quoteRepository.findById(2)).thenReturn(java.util.Optional.of(quote));
		quoteService.addRating(2);
		verify(quoteRepository).save(quote);
		assertEquals(quote.getRating(), 1);
	}

	@Test
	public void addDislikeToQuote() {
		Quote quote = new Quote(6, "oh no!","Me",0);

		when(quoteRepository.findById(2)).thenReturn(java.util.Optional.of(quote));
		quoteService.removeRating(2);
		verify(quoteRepository).save(quote);
		assertEquals(quote.getRating(), -1);
	}

}
