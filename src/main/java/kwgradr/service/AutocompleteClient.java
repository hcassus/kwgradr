package kwgradr.service;

import kwgradr.domain.SuggestionResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "autocomplete", url = "${amazon.api.url}")
public interface AutocompleteClient {

    @RequestMapping(method = RequestMethod.GET, path = "/suggestions")
    SuggestionResult getSuggestedCompletions(
            @RequestParam("prefix") String keyword,
            @RequestParam("alias") String alias,
            @RequestParam("mid") String marketId
    );

}
