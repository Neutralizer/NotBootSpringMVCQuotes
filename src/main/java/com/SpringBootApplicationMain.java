package com;

import com.dao.QuoteRepository;
import com.model.Quote;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class SpringBootApplicationMain {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplicationMain.class, args);
	}


	@Bean
	@Profile("prod")
	CommandLineRunner initData(QuoteRepository quoteRepository){
		return args -> {
			quoteRepository.save(new Quote(1,"It's over 9000!!!", "Vegeta","Movie",0));
			quoteRepository.save(new Quote(2,"I will defend my nakama!", "Luffy","Movie",0));
			quoteRepository.save(new Quote(3,"I smell meat!", "Luffy","Movie",1));
			quoteRepository.save(new Quote(4,"Omae wa mou shindeiru!","Kenshiro","Book",0));
			quoteRepository.save(new Quote(5,"I will be the pirate king!","Luffy","Movie",0));
		};
	}
}
