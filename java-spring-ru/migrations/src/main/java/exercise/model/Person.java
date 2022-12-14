package exercise.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record Person(
        @JsonProperty("id")
        Integer id,
        @JsonProperty("first_name")
        String firstname,
        @JsonProperty("last_name")
        String lastname
) {
}
