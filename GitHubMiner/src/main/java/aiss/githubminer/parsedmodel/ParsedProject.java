
package aiss.githubminer.parsedmodel;

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
    private String web_url;
    @JsonProperty("commits")
    private List<ParsedCommit> commits;
    @JsonProperty("issues")
    private List<ParsedIssue> issues;

    public ParsedProject(String id, String name, String web_url, List<ParsedCommit> commits, List<ParsedIssue> issues){
        this.id = id;
        this.name = name;
        this.web_url = web_url;
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
        return web_url;
    }

    @JsonProperty("web_url")
    public void setWebUrl(String webUrl) {
        this.web_url = web_url;
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
        StringBuilder sb = new StringBuilder();
        sb.append(ParsedProject.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("webUrl");
        sb.append('=');
        sb.append(((this.web_url == null)?"<null>":this.web_url));
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
