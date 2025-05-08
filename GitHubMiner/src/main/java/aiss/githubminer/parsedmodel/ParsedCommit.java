
package aiss.githubminer.parsedmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParsedCommit {

    @JsonProperty("sha")
    private String sha;
    @JsonProperty("title")
    private String title;
    @JsonProperty("message")
    private String message;
    @JsonProperty("authored_name")
    private String authored_name;
    @JsonProperty("authored_email")
    private String authored_email;
    @JsonProperty("authored_date")
    private String authored_date;
    @JsonProperty("url")
    private String url;

    public ParsedCommit(String sha, String title, String message, String authored_name, String authored_email, String authored_date, String url) {
        this.sha = sha;
        this.title = title;
        this.message = message;
        this.authored_name = authored_name;
        this.authored_email = authored_email;
        this.authored_date = authored_date;
        this.url = url;
    }

    @JsonProperty("sha")
    public String getSha() {
        return sha;
    }

    @JsonProperty("sha")
    public void setSha(String sha) {
        this.sha = sha;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("authored_name")
    public String getAuthored_name() {
        return authored_name;
    }

    @JsonProperty("authored_name")
    public void setAuthored_name(String authored_name) {
        this.authored_name = authored_name;
    }

    @JsonProperty("authored_email")
    public String getAuthored_email() {
        return authored_email;
    }

    @JsonProperty("authored_email")
    public void setAuthored_email(String authored_email) {
        this.authored_email = authored_email;
    }

    @JsonProperty("authored_date")
    public String getAuthored_date() {
        return authored_date;
    }

    @JsonProperty("authored_date")
    public void setAuthored_date(String authored_date) {
        this.authored_date = authored_date;
    }

    @Override
    public String toString() {
        return "ParsedCommit{" +
                "sha='" + sha + '\'' +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", url='" + url + '\'' +
                ", authored_name='" + authored_name + '\'' +
                ", authored_email='" + authored_email + '\'' +
                ", authored_date='" + authored_date + '\'' +
                '}';
    }
}
