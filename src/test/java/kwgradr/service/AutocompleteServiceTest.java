package kwgradr.service;

import kwgradr.domain.CompletionSuggestion;
import kwgradr.domain.SuggestionResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutocompleteServiceTest {

    @InjectMocks
    private AutocompleteService autocompleteService;

    @Mock
    private AutocompleteClient autocompleteClient;

    @Test
    void returnsSuggestionsForKeywordAsStrings(){
        String keyword = "iphone charger";
        SuggestionResult suggestionResult = buildSuggestions(keyword, "another completion suggestion", "a third one");
        when(autocompleteClient.getSuggestedCompletions(eq(keyword), any(), any())).thenReturn(suggestionResult);

        List<String> completionSuggestions = autocompleteService.getAutocompleteSuggestions(keyword);

        assertThat(completionSuggestions, hasSize(3));
        assertThat(completionSuggestions, hasItem(keyword));
    }

    private SuggestionResult buildSuggestions(String... keywords) {
        List<CompletionSuggestion> sugestions = Stream.of(keywords)
                .map(CompletionSuggestion::new)
                .collect(Collectors.toList());
        return new SuggestionResult(sugestions);
    }
}
