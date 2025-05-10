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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Integer getPagelen() {
        return pagelen;
    }

    public void setPagelen(Integer pagelen) {
        this.pagelen = pagelen;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
