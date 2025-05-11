package aiss.bitbucketminer.model.comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentPage {

    @JsonProperty("values")
    private List<Comment> comments;

    @JsonProperty("pagelen")
    private Integer pagelen;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("next")
    private String next;

    @JsonProperty("values")
    public List<Comment> getComments() {
        return comments;
    }

    @JsonProperty("values")
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @JsonProperty("pagelen")
    public Integer getPagelen() {
        return pagelen;
    }

    @JsonProperty("pagelen")
    public void setPagelen(Integer pagelen) {
        this.pagelen = pagelen;
    }

    @JsonProperty("size")
    public Integer getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(Integer size) {
        this.size = size;
    }

    @JsonProperty("page")
    public Integer getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(Integer page) {
        this.page = page;
    }

    @JsonProperty("next")
    public String getNext() {
        return next;
    }

    @JsonProperty("next")
    public void setNext(String next) {
        this.next = next;
    }
}
