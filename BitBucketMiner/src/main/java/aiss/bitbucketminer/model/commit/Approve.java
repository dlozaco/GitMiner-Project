
<<<<<<< HEAD:BitBucketMiner/src/main/java/aiss/bitbucketminer/model/commit/Approve.java
package aiss.bitbucketminer.model.commit;
=======
package aiss.bitbucketminer.model;
>>>>>>> 68b478e9e008a9d5df712026293de9022a2879a7:BitBucketMiner/src/main/java/aiss/bitbucketminer/model/Commits.java

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Approve {

    @JsonProperty("href")
    private String href;

    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    @JsonProperty("href")
    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Approve.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("href");
        sb.append('=');
        sb.append(((this.href == null)?"<null>":this.href));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
