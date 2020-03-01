package testQuotes;

import com.SpringBootApplicationMain;
import com.dao.QuoteRepository;
import com.model.Quote;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

@RunWith(SpringRunner.class)
@DataJpaTest()
@ContextConfiguration(classes = SpringBootApplicationMain.class)
public class QuoteRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private QuoteRepository quoteRepository;


    @Test
    public void whenFindAll_thenReturnQuotes_AndCheckSize() {
        testEntityManager.persist(new Quote("It's over KAKAROTTO!", "Vegeta", 0));
        testEntityManager.persist(new Quote("KROKODAILUU!", "Luffy", 0));
        testEntityManager.flush();

        List<Quote> found = (List<Quote>) quoteRepository.findAll();

        assertThat(found.size(), is(7));
    }

    @Test
    public void whenFindAllByAuthor_thenReturnQuotes_AndCheckSize() {
        List<Quote> found = quoteRepository.findByAuthor("Kenshiro");
        assertThat(found.size(), is(1));
    }

    @Test
    public void whenFindAllByRating_thenReturnQuotes_AndCheckSize() {
        List<Quote> found = quoteRepository.findByRating(1);
        assertThat(found.size(), is(1));
    }

    @Test
    public void whenFindAllByAuthorAndRating_thenReturnQuotes_AndCheckSize() {
        List<Quote> found = quoteRepository.findByAuthorAndRating("Luffy",1);
        assertThat(found.size(), is(1));
    }

    @Test
    public void whenFindById_thenReturnQuote_AndCheckQuoteName() {
        Optional<Quote> found = quoteRepository.findById(2);
        if(!found.isPresent()){
            throw new NoSuchElementException("Quote not found: " + 2);
        }

        assertThat(found.get().getQuote(), containsString("defend"));
    }

    @Test
    public void whenDeleteById_thenGetAll_AndCheckSize() {
        quoteRepository.deleteById(3);
        testEntityManager.flush();

        List<Quote> found = (List<Quote>) quoteRepository.findAll();
        assertThat(found.size(), is(4));
    }

    @Test
    public void whenPersist_thenGet_AndCheckQuoteName() {
        Quote vegeta = testEntityManager.persist(new Quote("It's over KAKAROTTO!", "Vegeta", 0));
        testEntityManager.flush();

        Optional<Quote> found = quoteRepository.findById(vegeta.getId());

        if(!found.isPresent()){
            throw new NoSuchElementException("Quote not found: " + vegeta.getId());
        }
        assertThat(found.get().getQuote(), containsString("over"));
    }

}
