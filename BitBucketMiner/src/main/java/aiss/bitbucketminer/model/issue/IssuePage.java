package aiss.bitbucketminer.model.issue;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IssuePage {

    @JsonProperty("values")
    private List<Issue> issues;

    @JsonProperty("pagelen")
    private Integer pagelen;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("page")
    private Integer page;

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
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
        return "IssuePage{" +
                "issues=" + issues +
                ", pagelen=" + pagelen +
                ", size=" + size +
                ", page=" + page +
                '}';
    }
}
