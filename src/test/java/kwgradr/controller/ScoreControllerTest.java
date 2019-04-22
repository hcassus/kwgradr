package kwgradr.controller;

import kwgradr.domain.KeywordScore;
import kwgradr.service.ScoreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ScoreController.class)
@ExtendWith(SpringExtension.class)
public class ScoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScoreService scoreService;

    @Test
    public void testEstimateController() throws Exception {
        String keyword = "iphone charger";
        Double score = 75d;

        given(scoreService.getKeywordScore(keyword)).willReturn(new KeywordScore(keyword, score));

        mockMvc.perform(get(String.format("/estimate?keyword=%s", keyword)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.score", is(score)))
                .andExpect(jsonPath("$.Keyword", is(keyword)));
    }

}
