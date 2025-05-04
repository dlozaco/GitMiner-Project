
package aiss.githubminer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "messageHeadline",
    "message",
    "committedDate",
    "author",
    "url"
})

public class Commit {

    @JsonProperty("id")
    private String id;
    @JsonProperty("messageHeadline")
    private String messageHeadline;
    @JsonProperty("message")
    private String message;
    @JsonProperty("committedDate")
    private String committedDate;
    @JsonProperty("author")
    private Author author;
    @JsonProperty("url")
    private String url;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("messageHeadline")
    public String getMessageHeadline() {
        return messageHeadline;
    }

    @JsonProperty("messageHeadline")
    public void setMessageHeadline(String messageHeadline) {
        this.messageHeadline = messageHeadline;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("committedDate")
    public String getCommittedDate() {
        return committedDate;
    }

    @JsonProperty("committedDate")
    public void setCommittedDate(String committedDate) {
        this.committedDate = committedDate;
    }

    @JsonProperty("author")
    public Author getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(Author author) {
        this.author = author;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

}
