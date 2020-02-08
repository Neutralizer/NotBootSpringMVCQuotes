package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import javax.net.ssl.SSLEngineResult.Status;

import static org.mockito.Mockito.when;

import com.controller.QuoteController;
import com.dao.QuoteRepository;
import com.model.Quote;
import com.service.QuoteService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebApplicationContext.class})
@WebAppConfiguration
class TestController {

	@Mock
	private QuoteService quoteService;
	
	@InjectMocks
	private QuoteController quoteController;
	
	private MockMvc mockMvc;
	
	
	
	
	
	@Before
	void setUp() throws Exception {
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
		int id = 1;
		
//		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new QuoteController())
//			       .build();
		//TestController
		
//		assertNotNull(quoteController);
		
//		when(quoteService.getQuote(id)).thenReturn(new Quote(1,"da"));
		
		ArrayList<Quote> allQuotes = new ArrayList<Quote>();
		allQuotes.add(new Quote(1,"da"));
		
		when(quoteService.getAllQuotes()).thenReturn(allQuotes);

		
		mockMvc.perform(get("/quotes"))
		.andExpect(status().isOk())
		.andReturn();
//			.andExpect(content().string(containsString("my")));
	}
	
	@Test
	public void getSingleQuote() throws Exception {
		int id = 1;

		when(quoteService.getQuote(id)).thenReturn(new Quote(1,"da"));
		
//		mockMvc.perform(get("/quotes/1"))
//		.andExpect(status().isOk())
//		.andReturn();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
