package br.com.golden_awards_worst_movies.application.controller;

import br.com.golden_awards_worst_movies.application.service.MovieService;
import br.com.golden_awards_worst_movies.domain.dto.MovieRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MovieControllerTest {

    Logger LOG = LoggerFactory.getLogger(MovieControllerTest.class);

    private static final String URL = "/api/v1/movie";

    @MockBean
    MovieService movieService;

    @Autowired
    MockMvc mvc;

    @Test
    public void testCreateMovie(){
        try {
            mvc.perform(MockMvcRequestBuilders.post(URL)
                    .content(getJsonPayload())
                    .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated());
        } catch (Exception e) {
            LOG.error(e.getMessage(),e);
        }
    }

    public String getJsonPayload() throws JsonProcessingException {
        MovieRequest dto = new MovieRequest();
        dto.setYear("1990");
        dto.setTitle("Filme do GU");
        dto.setStudios("Gustavo");
        dto.setProducers("Dechechi");
        dto.setWinner("false");

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dto);
    }

}
