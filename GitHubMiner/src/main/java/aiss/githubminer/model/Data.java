
package aiss.githubminer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "repository"
})

public class Data {

    @JsonProperty("repository")
    private Project repository;

    @JsonProperty("repository")
    public Project getRepository() {
        return repository;
    }

    @JsonProperty("repository")
    public void setRepository(Project repository) {
        this.repository = repository;
    }

}
