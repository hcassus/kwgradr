package kwgradr.service;

import kwgradr.domain.CompletionSuggestion;
import kwgradr.domain.SuggestionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AutocompleteService {

    @Value("${amazon.api.alias}")
    private String apiAlias;

    @Value("${amazon.api.marketId}")
    private String apiMarketId;

    private final AutocompleteClient client;

    List<String> getAutocompleteSuggestions(String keyword){
        SuggestionResult suggestedCompletions = client.getSuggestedCompletions(keyword, apiAlias, apiMarketId);

        return suggestedCompletions.getSuggestions().stream()
                .map(CompletionSuggestion::getValue)
                .collect(Collectors.toList());
    }

}
