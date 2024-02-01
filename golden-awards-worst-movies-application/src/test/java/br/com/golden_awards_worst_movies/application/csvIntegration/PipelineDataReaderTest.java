package br.com.golden_awards_worst_movies.application.csvIntegration;

import br.com.golden_awards_worst_movies.application.service.MovieService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.yml")
@ActiveProfiles("test")
public class PipelineDataReaderTest {
    final MockMvc mvc;
    final MovieService movieService;

    private static final String MOVIE_URL = "/api/v1/movie/{id}";
    private static final String AWARD_URL = "/api/v1/awards";
    private static final String MOVIE_ID = "20";
    private static final String YEAR = "1982";
    private static final String TITLE = "The Pirate Movie";
    private static final String STUDIOS = "20th Century Fox";
    private static final String PRODUCERS = "David Joseph";
    private static final String WINNER = "false";
    private static final String MIN_PRODUCER = "Joel Silver";
    private static final String MIN_INTERVAL = "1";
    private static final String MIN_PREVIOUS = "1990";
    private static final String MIN_FOLLOWING = "1991";
    private static final String MAX_PRODUCER = "Matthew Vaughn";
    private static final String MAX_INTERVAL = "13";
    private static final String MAX_PREVIOUS = "2002";
    private static final String MAX_FOLLOWING = "2015";

    @Autowired
    public PipelineDataReaderTest(MockMvc mvc, MovieService movieService) {
        this.mvc = mvc;
        this.movieService = movieService;
    }

    @Test
    public void testAwardResult() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(AWARD_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.min[0].producer").value(MIN_PRODUCER))
                .andExpect(jsonPath("$.min[0].interval").value(MIN_INTERVAL))
                .andExpect(jsonPath("$.min[0].previousWin").value(MIN_PREVIOUS))
                .andExpect(jsonPath("$.min[0].followingWin").value(MIN_FOLLOWING))
                .andExpect(jsonPath("$.max[0].producer").value(MAX_PRODUCER))
                .andExpect(jsonPath("$.max[0].interval").value(MAX_INTERVAL))
                .andExpect(jsonPath("$.max[0].previousWin").value(MAX_PREVIOUS))
                .andExpect(jsonPath("$.max[0].followingWin").value(MAX_FOLLOWING))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void findSpecificMovie() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(MOVIE_URL, MOVIE_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(MOVIE_ID))
                .andExpect(jsonPath("$.year").value(YEAR))
                .andExpect(jsonPath("$.title").value(TITLE))
                .andExpect(jsonPath("$.winner").value(WINNER))
                .andExpect(jsonPath("$.studios[0].name").value(STUDIOS))
                .andExpect(jsonPath("$.producers[0].name").value(PRODUCERS))
                .andDo(MockMvcResultHandlers.print());
    }
}
