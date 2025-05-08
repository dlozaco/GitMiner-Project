
package aiss.githubminer.parsedmodel;

import aiss.githubminer.model.commit.Commit;
import aiss.githubminer.model.issue.Issue;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParsedProject {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("html_url")
    private String htmlUrl;
    @JsonProperty("commits")
    private List<ParsedCommit> commits;
    @JsonProperty("issues")
    private List<Issue> issues;

    public ParsedProject(Integer id, String name, String htmlUrl, List<ParsedCommit> commits, List<Issue> issues){
        this.id = id;
        this.name = name;
        this.htmlUrl = htmlUrl;
        this.commits = commits;
        this.issues = issues;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
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

    @JsonProperty("html_url")
    public String getHtmlUrl() {
        return htmlUrl;
    }

    @JsonProperty("html_url")
    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ParsedProject.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("htmlUrl");
        sb.append('=');
        sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
