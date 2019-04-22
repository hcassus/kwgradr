package kwgradr.service;

import kwgradr.domain.KeywordScore;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScoreServiceTest {

    @Mock
    private AutocompleteService autocompleteService;

    @InjectMocks
    private ScoreService scoreService;

    @Test
    void keywordNotFoundScoreZero(){
        String keyword = "iphone charger";
        when(autocompleteService.getAutocompleteSuggestions(keyword)).thenReturn(emptyList());

        KeywordScore score = scoreService.getKeywordScore(keyword);

        assertThat(score.getKeyword(), Matchers.is(keyword));
        assertThat(score.getScore(), Matchers.is(0d));
    }

    @Test
    void keywordAndFirstLetterReturnSuggestionMaxScore(){
        String keyword = "iphone charger";
        when(autocompleteService.getAutocompleteSuggestions(any())).thenReturn(singletonList(keyword));

        KeywordScore score = scoreService.getKeywordScore(keyword);

        assertThat(score.getKeyword(), Matchers.is(keyword));
        assertThat(score.getScore(), Matchers.is(100d));
    }

    @Test
    void keywordAppearsOnlyAfterHalfIsTypedScoresFifty(){
        String keyword = "iphone charger";
        Answer<List<String>> answer = invocation -> {
            String string = invocation.getArgument(0, String.class);
            return string.length() <= keyword.length()/2 ? emptyList() : singletonList(keyword);
        };
        when(autocompleteService.getAutocompleteSuggestions(any())).thenAnswer(answer);

        KeywordScore score = scoreService.getKeywordScore(keyword);

        assertThat(score.getKeyword(), Matchers.is(keyword));
        assertThat(score.getScore(), Matchers.is(50d));
    }

}