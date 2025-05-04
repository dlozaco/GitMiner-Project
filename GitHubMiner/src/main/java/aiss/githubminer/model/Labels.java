
package aiss.githubminer.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "nodes"
})

public class Labels {

    @JsonProperty("nodes")
    private List<Object> nodes;

    @JsonProperty("nodes")
    public List<Object> getNodes() {
        return nodes;
    }

    @JsonProperty("nodes")
    public void setNodes(List<Object> nodes) {
        this.nodes = nodes;
    }

}
