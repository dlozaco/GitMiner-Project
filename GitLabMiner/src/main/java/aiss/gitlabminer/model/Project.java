package aiss.gitlabminer.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

/*For the request of a project by its owner and name, the path https://gitlab.com/api/v4/projects/ only takes one
argument which is the full path owner/project, so, for the path to not process each one as a different argument, / must be
replaced by %2F*/

@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {

//    id in GitMiner model
    @JsonProperty("id")
    private String id;

//    name in GitMiner model
    @JsonProperty("name")
    @NotEmpty(message = "The field name cannot be empty.")
    private String name;

//    web_url in GitMiner model
    @JsonProperty("web_url")
    @NotEmpty(message = "The field web_url cannot be empty.")
    private String webUrl;

    @JsonProperty("commits")
    private List<Commit> commits;

    @JsonProperty("issues")
    private List<IssueParsed> issues;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("web_url")
    public String getWebUrl() {
        return webUrl;
    }

    @JsonProperty("web_url")
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    @JsonProperty("commits")
    public List<Commit> getCommits() {
        return commits;
    }

    @JsonProperty("commits")
    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }

    @JsonProperty("issues")
    public List<IssueParsed> getIssues() {
        return issues;
    }

    @JsonProperty("issues")
    public void setIssues(List<IssueParsed> issues) {
        this.issues = issues;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Commit.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("webUrl");
        sb.append('=');
        sb.append(((this.webUrl == null)?"<null>":this.webUrl));
        sb.append(',');
        sb.append('\n');
        sb.append("commits");
        sb.append('=');
        sb.append(((this.commits == null)?"<null>":this.commits));
        sb.append(',');
        sb.append('\n');
        sb.append("issues");
        sb.append('=');
        sb.append(((this.issues == null)?"<null>":this.issues));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
