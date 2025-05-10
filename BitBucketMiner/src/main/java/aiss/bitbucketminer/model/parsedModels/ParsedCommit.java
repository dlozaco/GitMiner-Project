package aiss.bitbucketminer.model.parsedModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParsedCommit {

    @JsonProperty("hash")
    private String hash;
    @JsonProperty("title")
    private String title;
    @JsonProperty("message")
    private String message;
    @JsonProperty("author_name")
    private String author_name;
    @JsonProperty("author_email")
    private String author_email;
    @JsonProperty("authored_date")
    private String authored_date;
    @JsonProperty("web_url")
    private String webUrl;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getAuthor_email() {
        return author_email;
    }

    public void setAuthor_email(String author_email) {
        this.author_email = author_email;
    }

    public String getAuthored_date() {
        return authored_date;
    }

    public void setAuthored_date(String authored_date) {
        this.authored_date = authored_date;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public ParsedCommit(String hash, String title, String message, String author_name, String author_email, String authored_date, String webUrl) {
        this.hash = hash;
        this.title = title;
        this.message = message;
        this.author_name = author_name;
        this.author_email = author_email;
        this.authored_date = authored_date;
        this.webUrl = webUrl;
    }

    @Override
    public String toString() {
        return "ParsedCommit{" +
                "hash='" + hash + '\'' +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", author_name='" + author_name + '\'' +
                ", author_email='" + author_email + '\'' +
                ", authored_date='" + authored_date + '\'' +
                ", webUrl='" + webUrl + '\'' +
                '}';
    }
}
