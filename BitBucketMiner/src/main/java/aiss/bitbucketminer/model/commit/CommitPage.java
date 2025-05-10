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

    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
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
