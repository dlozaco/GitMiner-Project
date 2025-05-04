
package aiss.githubminer.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "nodes"
})

public class Comments {

    @JsonProperty("nodes")
    private List<Comment> nodes;

    @JsonProperty("nodes")
    public List<Comment> getNodes() {
        return nodes;
    }

    @JsonProperty("nodes")
    public void setNodes(List<Comment> nodes) {
        this.nodes = nodes;
    }

}
