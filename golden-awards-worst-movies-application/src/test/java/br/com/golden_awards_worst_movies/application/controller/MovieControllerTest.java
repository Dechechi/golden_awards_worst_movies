package br.com.golden_awards_worst_movies.application.controller;

import br.com.golden_awards_worst_movies.application.service.MovieService;
import br.com.golden_awards_worst_movies.domain.dto.MovieRequest;
import br.com.golden_awards_worst_movies.domain.enums.WinnerBoolean;
import br.com.golden_awards_worst_movies.domain.exception.InvalidWinnerOptionException;
import br.com.golden_awards_worst_movies.domain.model.Movie;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
public class MovieControllerTest {
    private static final String URL = "/api/v1/movie";

    private static final String YEAR = "1990";
    private static final String TITLE = "Filme de Teste";
    private static final String STUDIOS = "Studio de Teste";
    private static final String PRODUCERS = "Test Producer Films";
    private static final String WINNER = "false";

    @MockBean
    MovieService movieService;

    @Autowired
    MockMvc mvc;

    @Test
    public void testCreateMovie() throws Exception {

        BDDMockito.given(movieService.createMovie(Mockito.any(Movie.class))).willReturn(getMockMovie());

        mvc.perform(MockMvcRequestBuilders.post(URL)
                .content(getJsonPayload())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.year").value(YEAR))
                .andExpect(jsonPath("$.title").value(TITLE))
                .andExpect(jsonPath("$.winner").value(WINNER))
                .andExpect(jsonPath("$.studios[0].name").value(STUDIOS))
                .andExpect(jsonPath("$.producers[0].name").value(PRODUCERS))
                .andDo(MockMvcResultHandlers.print());
    }

    public String getJsonPayload() throws JsonProcessingException {
        MovieRequest dto = new MovieRequest();
        dto.setYear(YEAR);
        dto.setTitle(TITLE);
        dto.setStudios(STUDIOS);
        dto.setProducers(PRODUCERS);
        dto.setWinner(WINNER);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dto);
    }

    private Movie getMockMovie() throws InvalidWinnerOptionException {
        return Movie.Builder.builder().withId(1L)
                .withTitle(TITLE)
                .withYear(Integer.parseInt(YEAR))
                .withWinner(WinnerBoolean.valueFrom(WINNER))
                .withStudiosFromString(STUDIOS)
                .withProducersFromString(PRODUCERS, YEAR, WINNER)
                .build();
    }

}
