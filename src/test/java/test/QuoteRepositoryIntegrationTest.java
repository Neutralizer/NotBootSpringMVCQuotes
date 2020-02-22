package test;

import com.SpringBootApplicationMain;
import com.dao.QuoteRepository;
import com.model.Quote;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest()
@ContextConfiguration(classes = SpringBootApplicationMain.class)
public class QuoteRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private QuoteRepository quoteRepository;


    @Test
    public void whenFindAll_thenReturnQuotes(){
        testEntityManager.persist(new Quote("It's over KAKAROTTO!", "Vegeta", 0));
        testEntityManager.persist(new Quote("KROKODAILUU!", "Luffy", 0));
        testEntityManager.flush();

        List<Quote> found = (List<Quote>) quoteRepository.findAll();

        assertThat(found.size(), is(7));
    }

    @Test
    public void whenGet_thenReturnQuotes(){

    }

}
