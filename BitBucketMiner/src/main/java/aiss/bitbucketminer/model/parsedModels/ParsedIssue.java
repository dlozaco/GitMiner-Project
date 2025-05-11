package aiss.bitbucketminer.model.parsedModels;

import aiss.bitbucketminer.model.comment.Comment;
import aiss.bitbucketminer.model.issue.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParsedIssue {

    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("state")
    private String state;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("closed_at")
    private Object closedAt;
    @JsonProperty("labels")
    private List<String> labels;
    @JsonProperty("votes")
    private Integer votes;

    @JsonProperty("author")
    private ParsedUser author;
    @JsonProperty("assignee")
    private ParsedUser assignee;
    @JsonProperty("comments")
    private List<ParsedComment> comments;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("closed_at")
    public Object getClosedAt() {
        return closedAt;
    }

    @JsonProperty("closed_at")
    public void setClosedAt(Object closedAt) {
        this.closedAt = closedAt;
    }

    @JsonProperty("labels")
    public List<String> getLabels() {
        return labels;
    }

    @JsonProperty("labels")
    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    @JsonProperty("votes")
    public Integer getVotes() {
        return votes;
    }

    @JsonProperty("votes")
    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    @JsonProperty("author")
    public ParsedUser getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(ParsedUser user) {
        this.author = user;
    }

    @JsonProperty("assignee")
    public ParsedUser getAssignee() {
        return assignee;
    }

    @JsonProperty("assignee")
    public void setAssignee(ParsedUser assignee) {
        this.assignee = assignee;
    }

    @JsonProperty("comments")
    public List<ParsedComment> getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(List<ParsedComment> comments) {
        this.comments = comments;
    }

    public ParsedIssue(String id, String title, String description, String state, String createdAt, String updatedAt, Object closedAt, List<String> labels, Integer votes, ParsedUser author, ParsedUser assignee, List<ParsedComment> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.state = state;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.closedAt = closedAt;
        this.labels = labels;
        this.votes = votes;
        this.author = author;
        this.assignee = assignee;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "ParsedIssue{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", closedAt=" + closedAt +
                ", labels=" + labels +
                ", votes=" + votes +
                ", author=" + author +
                ", assignee=" + assignee +
                ", comments=" + comments +
                '}';
    }
}
