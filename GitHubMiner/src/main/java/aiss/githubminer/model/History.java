
package aiss.githubminer.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "nodes"
})

public class History {

    @JsonProperty("nodes")
    private List<Commit> nodes;

    @JsonProperty("nodes")
    public List<Commit> getNodes() {
        return nodes;
    }

    @JsonProperty("nodes")
    public void setNodes(List<Commit> nodes) {
        this.nodes = nodes;
    }

}
