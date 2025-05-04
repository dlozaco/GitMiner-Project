
package aiss.githubminer.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "nodes"
})

public class Issues {

    @JsonProperty("nodes")
    private List<Issue> nodes;

    @JsonProperty("nodes")
    public List<Issue> getNodes() {
        return nodes;
    }

    @JsonProperty("nodes")
    public void setNodes(List<Issue> nodes) {
        this.nodes = nodes;
    }

}
