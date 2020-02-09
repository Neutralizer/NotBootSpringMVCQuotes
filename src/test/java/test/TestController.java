package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.Mockito.when;

import static org.hamcrest.core.StringContains.containsString;

import com.controller.QuoteController;
import com.model.Quote;
import com.service.QuoteService;


class TestController {

	@Mock
	private QuoteService quoteService;
	
	@InjectMocks
	private QuoteController quoteController;
	
	private MockMvc mockMvc;
	
	
	@BeforeEach
	void setUp(){
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(quoteController).build();
	}
	
//	@Test
//	public void getAllTest() throws Exception {
//		Quote quote = new Quote(6,"ddsfsd");
//        mockMvc.perform(get("/quotes")).andExpect(status().isOk())
//        .andExpect(content().string(containsString("meat")));
//	}
	
	
	@Test
	public void getAllQuotes() throws Exception {
		
		ArrayList<Quote> allQuotes = new ArrayList<Quote>();
		allQuotes.add(new Quote(1,"da"));
		allQuotes.add(new Quote(2,"bo"));
		
		when(quoteService.getAllQuotes()).thenReturn((List<Quote>)allQuotes);

		
		mockMvc.perform(get("/quotes"))
		.andDo(print())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("da")))
		.andExpect(content().string(containsString("2")));
	}
	
	@Test
	public void getSingleQuote() throws Exception {
		int id = 1;

		when(quoteService.getQuote(id)).thenReturn(new Quote(id,"si"));
		
		mockMvc.perform(get("/quotes/1"))
		.andDo(print())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("si")))
		.andExpect(content().string(containsString("1")))
		.andReturn();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
