package aiss.bitbucketminer.model.parsedModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParsedProject {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("web_url")
    private String webUrl;

    @JsonProperty("commits")
    private List<ParsedCommit> commits;

    @JsonProperty("issues")
    private List<ParsedIssue> issues;

    public ParsedProject(String id, String name, String webUrl, List<ParsedCommit> commits, List<ParsedIssue> issues) {
        this.id = id;
        this.name = name;
        this.webUrl = webUrl;
        this.commits = commits;
        this.issues = issues;
    }

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
    public List<ParsedCommit> getCommits() {
        return commits;
    }

    @JsonProperty("commits")
    public void setCommits(List<ParsedCommit> commits) {
        this.commits = commits;
    }

    @JsonProperty("issues")
    public List<ParsedIssue> getIssues() {
        return issues;
    }

    @JsonProperty("issues")
    public void setIssues(List<ParsedIssue> issues) {
        this.issues = issues;
    }

    @Override
    public String toString() {
        return "ParsedProject{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", commits=" + commits +
                ", issues=" + issues +
                '}';
    }
}
