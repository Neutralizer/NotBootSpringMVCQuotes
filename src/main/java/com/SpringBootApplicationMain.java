package com;

import com.dao.QuoteRepository;
import com.model.Quote;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootApplicationMain {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplicationMain.class, args);
	}


	@Bean
	CommandLineRunner initData(QuoteRepository quoteRepository){
		return args -> {
			quoteRepository.save(new Quote("It's over 9000!!!", "Vegeta",0));
			quoteRepository.save(new Quote("I will defend my nakama!", "Luffy",0));
			quoteRepository.save(new Quote("I smell meat!", "Luffy",0));
			quoteRepository.save(new Quote("Omae wa mou shindeiru!","Kenshiro",0));
			quoteRepository.save(new Quote("I will be the pirate king!","Luffy",0));
		};
	}
}
