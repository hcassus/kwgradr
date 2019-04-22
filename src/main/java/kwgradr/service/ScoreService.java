package kwgradr.service;

import kwgradr.domain.KeywordScore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreService {

    private final AutocompleteService autocompleteService;

    public KeywordScore getKeywordScore(String keyword) {
        Double score = calculateScore(keyword.toLowerCase());
        return new KeywordScore(keyword, score);
    }

    private Double calculateScore(String keyword) {
        List<String> keywordSuggestions = autocompleteService.getAutocompleteSuggestions(keyword);
        if(!keywordSuggestions.contains(keyword)){
            return 0d;
        }

        List<String> firstLetterSuggestions = autocompleteService.getAutocompleteSuggestions(keyword.substring(0,1));
        if (firstLetterSuggestions.contains(keyword)){
            return 100d;
        }

        return calculateRollingScore(keyword);
    }

    private Double calculateRollingScore(String keyword) {
        int keywordLength = keyword.length();

        Double result = 100d;

        for(int i=keywordLength; i > 0; i--){
            List<String> suggestions = autocompleteService.getAutocompleteSuggestions(keyword.substring(0, i));

            if(!suggestions.contains(keyword)){
                Double factor = 1 - i/(double)keywordLength;
                result *= factor;
                break;
            }

        }

        return result;
    }
}
