package kwgradr.controller;

import kwgradr.domain.KeywordScore;
import kwgradr.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService scoreService;

    @RequestMapping(method = RequestMethod.GET, path = "/estimate")
    KeywordScore calculateScore(@RequestParam("keyword") String keyword){
        return scoreService.getKeywordScore(keyword);
    }

}
