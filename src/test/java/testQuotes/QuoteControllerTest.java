package testQuotes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

import static org.hamcrest.core.StringContains.containsString;

import com.controller.QuoteController;
import com.google.gson.Gson;
import com.model.Quote;
import com.service.QuoteService;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Profile("dev")
class QuoteControllerTest {

	@Mock
	private QuoteService quoteService;

	@InjectMocks
	private QuoteController quoteController;

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(quoteController).build();
	}

	@Test
	public void getAllQuotes() throws Exception {

		ArrayList<Quote> allQuotes = new ArrayList<>();
		allQuotes.add(new Quote(1, "da","Me", "Movie",0));
		allQuotes.add(new Quote(2, "bo","Him","Movie",25));

		when(quoteService.getAllQuotes()).thenReturn(allQuotes);
		String uri = UriComponentsBuilder.newInstance().path("/quotes").build().toUriString();

		mockMvc.perform(get(uri)).andDo(print()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().string(containsString("da")))
				.andExpect(content().string(containsString("2")))
				.andExpect(content().string(containsString("Him")))
				.andExpect(content().string(containsString("Movie")))
				.andExpect(content().string(containsString("25")));
	}

	@Test
	public void getAllQuotesByAuthor() throws Exception {

		ArrayList<Quote> allQuotes = new ArrayList<>();
		allQuotes.add(new Quote(1, "da","Me", "Movie",0));
		allQuotes.add(new Quote(2, "bo","Me","Movie",25));

		when(quoteService.getAllQuotesOfAuthor("Me")).thenReturn(allQuotes);

		String uri = UriComponentsBuilder.newInstance().path("/quotes").query("author={author}")
				.buildAndExpand("Me").toUriString();

		mockMvc.perform(get(uri)).andDo(print()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("da")))
				.andExpect(content().string(containsString("bo")))
				.andExpect(content().string(containsString("Me")))
				.andExpect(content().string(containsString("Movie")))
				.andExpect(content().string(containsString("25")));
	}

	@Test
	public void getAllQuotesWithRating() throws Exception {

		ArrayList<Quote> allQuotes = new ArrayList<>();
		allQuotes.add(new Quote(1, "da","Me", "Movie",0));
		allQuotes.add(new Quote(2, "bo","Me","Movie",0));

		when(quoteService.getAllQuotesWithRating(0)).thenReturn(allQuotes);

		String uri = UriComponentsBuilder.newInstance().path("/quotes").query("rating={rating}")
				.buildAndExpand("0").toUriString();

		mockMvc.perform(get(uri)).andDo(print()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("da")))
				.andExpect(content().string(containsString("bo")))
				.andExpect(content().string(containsString("Me")))
				.andExpect(content().string(containsString("Movie")))
				.andExpect(content().string(containsString("0")));
	}

	@Test
	public void getAllQuotesOfAuthorWithRating() throws Exception {

		ArrayList<Quote> allQuotes = new ArrayList<>();
		allQuotes.add(new Quote(1, "da","Me", "Movie",0));
		allQuotes.add(new Quote(2, "bo","Me","Movie",0));

		when(quoteService.getAllQuotesOfAuthorWithRating("Me",0)).thenReturn(allQuotes);

		String uri = UriComponentsBuilder.newInstance().path("/quotes").query("author={author}")
				.query("rating={rating}").buildAndExpand("Me","0").toUriString();

		mockMvc.perform(get(uri)).andDo(print()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("da")))
				.andExpect(content().string(containsString("bo")))
				.andExpect(content().string(containsString("Me")))
				.andExpect(content().string(containsString("Movie")))
				.andExpect(content().string(containsString("0")));
	}

	@Test
	public void getAllQuotesOfAuthorWithRatingFromSource() throws Exception {

		ArrayList<Quote> allQuotes = new ArrayList<>();
		allQuotes.add(new Quote(1, "da","Me", "Movie",0));
		allQuotes.add(new Quote(2, "bo","Me","Movie",0));

		when(quoteService.getAllQuotesOfAuthorWithRatingFromSource("Me","Movie",0)).thenReturn(allQuotes);

		String uri = UriComponentsBuilder.newInstance().path("/quotes").query("author={author}")
				.query("source={source}").query("rating={rating}").buildAndExpand("Me","Movie","0").toUriString();

		mockMvc.perform(get(uri)).andDo(print()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("da")))
				.andExpect(content().string(containsString("bo")))
				.andExpect(content().string(containsString("Me")))
				.andExpect(content().string(containsString("Movie")))
				.andExpect(content().string(containsString("0")));
	}

	@Test
	public void getAllQuotesOfAuthorFromSource() throws Exception {

		ArrayList<Quote> allQuotes = new ArrayList<>();
		allQuotes.add(new Quote(1, "da","Me", "Movie",0));
		allQuotes.add(new Quote(2, "bo","Me","Movie",0));

		when(quoteService.getAllQuotesOfAuthorFromSource("Me","Movie")).thenReturn(allQuotes);

		String uri = UriComponentsBuilder.newInstance().path("/quotes").query("author={author}")
				.query("source={source}").buildAndExpand("Me","Movie").toUriString();

		mockMvc.perform(get(uri)).andDo(print()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("da")))
				.andExpect(content().string(containsString("bo")))
				.andExpect(content().string(containsString("Me")))
				.andExpect(content().string(containsString("Movie")))
				.andExpect(content().string(containsString("0")));
	}

	@Test
	public void getAllQuotesFromSourceWithRating() throws Exception {

		ArrayList<Quote> allQuotes = new ArrayList<>();
		allQuotes.add(new Quote(1, "da","Me", "Movie",0));
		allQuotes.add(new Quote(2, "bo","Me","Movie",0));

		when(quoteService.getAllQuotesFromSourceWithRating("Movie",0)).thenReturn(allQuotes);

		String uri = UriComponentsBuilder.newInstance().path("/quotes").query("source={source}")
				.query("rating={rating}").buildAndExpand("Movie","0").toUriString();

		mockMvc.perform(get(uri)).andDo(print()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("da")))
				.andExpect(content().string(containsString("bo")))
				.andExpect(content().string(containsString("Me")))
				.andExpect(content().string(containsString("Movie")))
				.andExpect(content().string(containsString("0")));
	}

	@Test
	public void getAllQuotesFromSource() throws Exception {

		ArrayList<Quote> allQuotes = new ArrayList<>();
		allQuotes.add(new Quote(1, "da","Me", "Movie",0));
		allQuotes.add(new Quote(2, "bo","Me","Movie",0));

		when(quoteService.getAllQuotesFromSource("Movie")).thenReturn(allQuotes);

		String uri = UriComponentsBuilder.newInstance().path("/quotes").query("source={source}")
				.buildAndExpand("Movie").toUriString();

		mockMvc.perform(get(uri)).andDo(print()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("da")))
				.andExpect(content().string(containsString("bo")))
				.andExpect(content().string(containsString("Me")))
				.andExpect(content().string(containsString("Movie")))
				.andExpect(content().string(containsString("0")));
	}

	@Test
	public void getSingleQuote() throws Exception {
		int id = 1;

		when(quoteService.getQuote(id)).thenReturn(new Quote(id, "si","Me",
				"Movie",0));
		String uri = UriComponentsBuilder.newInstance().path("/quotes/{id}")
				.buildAndExpand(id).toUriString();

		mockMvc.perform(get(uri)).andDo(print())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(containsString("si")))
				.andExpect(content().string(containsString("1")))
				.andExpect(content().string(containsString("Me")))
				.andExpect(content().string(containsString("Movie")))
				.andExpect(content().string(containsString("0")))
				.andReturn();

	}

	@Test
	public void deleteSingleQuote() throws Exception {
		String uri = UriComponentsBuilder.newInstance().path("/quotes/{id}")
				.buildAndExpand(1).toUriString();
		mockMvc.perform(delete(uri)).andExpect(status().isOk());

	}

	@Test
	public void postQuoteTest() throws Exception {
		Quote quote = new Quote(6, "baobab","Me","Movie",-3);

		Gson gson = new Gson();
		String json = gson.toJson(quote);
		String uri = UriComponentsBuilder.newInstance().path("/quotes")
				.build().toUriString();

		MockHttpServletRequestBuilder request = post(uri);
		request.content(json);
		request.accept(MediaType.APPLICATION_JSON);
		request.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andDo(print()).andExpect(status().isOk());

	}

    @Test
    public void likeQuote() throws Exception {
		String uri = UriComponentsBuilder.newInstance().path("/quotes/{id}/like")
				.buildAndExpand(1).toUriString();

        MockHttpServletRequestBuilder request = post(uri);
        mockMvc.perform(request).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void dislikeQuote() throws Exception {
		String uri = UriComponentsBuilder.newInstance().path("/quotes/{id}/dislike")
				.buildAndExpand(1).toUriString();

        MockHttpServletRequestBuilder request = post(uri);
        mockMvc.perform(request).andDo(print()).andExpect(status().isOk());
    }

}
