package aiss.bitbucketminer.model.commit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommitPage {

    @JsonProperty("values")
    private List<Commit> commits;

    @JsonProperty("pagelen")
    private Integer pagelen;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("next")
    private String next;

    @JsonProperty("values")
    public List<Commit> getCommits() {
        return commits;
    }

    @JsonProperty("values")
    public void setCommits(List<Commit> commits) {
        this.commits = commits;
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

    @Override
    public String toString() {
        return "CommitPage{" +
                "commits=" + commits +
                ", pagelen=" + pagelen +
                ", size=" + size +
                ", page=" + page +
                '}';
    }
}
