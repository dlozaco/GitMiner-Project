
package aiss.bitbucketminer.model.project;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Links__1 {

    @JsonProperty("avatar")
    private Avatar__1 avatar;
    @JsonProperty("html")
    private Html__1 html;
    @JsonProperty("self")
    private Self__1 self;

    @JsonProperty("avatar")
    public Avatar__1 getAvatar() {
        return avatar;
    }

    @JsonProperty("avatar")
    public void setAvatar(Avatar__1 avatar) {
        this.avatar = avatar;
    }

    @JsonProperty("html")
    public Html__1 getHtml() {
        return html;
    }

    @JsonProperty("html")
    public void setHtml(Html__1 html) {
        this.html = html;
    }

    @JsonProperty("self")
    public Self__1 getSelf() {
        return self;
    }

    @JsonProperty("self")
    public void setSelf(Self__1 self) {
        this.self = self;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Links__1 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("avatar");
        sb.append('=');
        sb.append(((this.avatar == null)?"<null>":this.avatar));
        sb.append(',');
        sb.append("html");
        sb.append('=');
        sb.append(((this.html == null)?"<null>":this.html));
        sb.append(',');
        sb.append("self");
        sb.append('=');
        sb.append(((this.self == null)?"<null>":this.self));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
