package kwgradr.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder(alphabetic = true)
public class KeywordScore {
    @JsonProperty("Keyword")
    private final String keyword;
    private final Double score;
}
