package aiss.gitlabminer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {

//    id in GitMiner model
    @JsonProperty("id")
    private Long id;

    @JsonProperty("type")
    private Object type;

//    body in GitMiner model
    @JsonProperty("body")
    private String body;

//    author in GitMiner model
    @JsonProperty("author")
    private User author;

//    created_at in GitMiner model
    @JsonProperty("created_at")
    private String createdAt;

//    updated_at in GitMiner model
    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("system")
    private Boolean system;

    @JsonProperty("noteable_id")
    private Integer noteableId;

    @JsonProperty("noteable_type")
    private String noteableType;

    @JsonProperty("project_id")
    private Integer projectId;

    @JsonProperty("resolvable")
    private Boolean resolvable;

    @JsonProperty("confidential")
    private Boolean confidential;

    @JsonProperty("internal")
    private Boolean internal;

    @JsonProperty("imported")
    private Boolean imported;

    @JsonProperty("imported_from")
    private String importedFrom;

    @JsonProperty("noteable_iid")
    private Integer noteableIid;

    @JsonProperty("resolved")
    private Boolean resolved;

    @JsonProperty("resolved_by")
    private Object resolvedBy;

    @JsonProperty("resolved_at")
    private Object resolvedAt;

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("type")
    public Object getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(Object type) {
        this.type = type;
    }

    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

    @JsonProperty("author")
    public User getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(User author) {
        this.author = author;
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

    @JsonProperty("system")
    public Boolean getSystem() {
        return system;
    }

    @JsonProperty("system")
    public void setSystem(Boolean system) {
        this.system = system;
    }

    @JsonProperty("noteable_id")
    public Integer getNoteableId() {
        return noteableId;
    }

    @JsonProperty("noteable_id")
    public void setNoteableId(Integer noteableId) {
        this.noteableId = noteableId;
    }

    @JsonProperty("noteable_type")
    public String getNoteableType() {
        return noteableType;
    }

    @JsonProperty("noteable_type")
    public void setNoteableType(String noteableType) {
        this.noteableType = noteableType;
    }

    @JsonProperty("project_id")
    public Integer getProjectId() {
        return projectId;
    }

    @JsonProperty("project_id")
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @JsonProperty("resolvable")
    public Boolean getResolvable() {
        return resolvable;
    }

    @JsonProperty("resolvable")
    public void setResolvable(Boolean resolvable) {
        this.resolvable = resolvable;
    }

    @JsonProperty("confidential")
    public Boolean getConfidential() {
        return confidential;
    }

    @JsonProperty("confidential")
    public void setConfidential(Boolean confidential) {
        this.confidential = confidential;
    }

    @JsonProperty("internal")
    public Boolean getInternal() {
        return internal;
    }

    @JsonProperty("internal")
    public void setInternal(Boolean internal) {
        this.internal = internal;
    }

    @JsonProperty("imported")
    public Boolean getImported() {
        return imported;
    }

    @JsonProperty("imported")
    public void setImported(Boolean imported) {
        this.imported = imported;
    }

    @JsonProperty("imported_from")
    public String getImportedFrom() {
        return importedFrom;
    }

    @JsonProperty("imported_from")
    public void setImportedFrom(String importedFrom) {
        this.importedFrom = importedFrom;
    }

    @JsonProperty("noteable_iid")
    public Integer getNoteableIid() {
        return noteableIid;
    }

    @JsonProperty("noteable_iid")
    public void setNoteableIid(Integer noteableIid) {
        this.noteableIid = noteableIid;
    }

    @JsonProperty("resolved")
    public Boolean getResolved() {
        return resolved;
    }

    @JsonProperty("resolved")
    public void setResolved(Boolean resolved) {
        this.resolved = resolved;
    }

    @JsonProperty("resolved_by")
    public Object getResolvedBy() {
        return resolvedBy;
    }

    @JsonProperty("resolved_by")
    public void setResolvedBy(Object resolvedBy) {
        this.resolvedBy = resolvedBy;
    }

    @JsonProperty("resolved_at")
    public Object getResolvedAt() {
        return resolvedAt;
    }

    @JsonProperty("resolved_at")
    public void setResolvedAt(Object resolvedAt) {
        this.resolvedAt = resolvedAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Comment.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("body");
        sb.append('=');
        sb.append(((this.body == null)?"<null>":this.body));
        sb.append(',');
        sb.append("author");
        sb.append('=');
        sb.append(((this.author == null)?"<null>":this.author));
        sb.append(',');
        sb.append("createdAt");
        sb.append('=');
        sb.append(((this.createdAt == null)?"<null>":this.createdAt));
        sb.append(',');
        sb.append("updatedAt");
        sb.append('=');
        sb.append(((this.updatedAt == null)?"<null>":this.updatedAt));
        sb.append(',');
        sb.append("system");
        sb.append('=');
        sb.append(((this.system == null)?"<null>":this.system));
        sb.append(',');
        sb.append("noteableId");
        sb.append('=');
        sb.append(((this.noteableId == null)?"<null>":this.noteableId));
        sb.append(',');
        sb.append("noteableType");
        sb.append('=');
        sb.append(((this.noteableType == null)?"<null>":this.noteableType));
        sb.append(',');
        sb.append("projectId");
        sb.append('=');
        sb.append(((this.projectId == null)?"<null>":this.projectId));
        sb.append(',');
        sb.append("resolvable");
        sb.append('=');
        sb.append(((this.resolvable == null)?"<null>":this.resolvable));
        sb.append(',');
        sb.append("confidential");
        sb.append('=');
        sb.append(((this.confidential == null)?"<null>":this.confidential));
        sb.append(',');
        sb.append("internal");
        sb.append('=');
        sb.append(((this.internal == null)?"<null>":this.internal));
        sb.append(',');
        sb.append("imported");
        sb.append('=');
        sb.append(((this.imported == null)?"<null>":this.imported));
        sb.append(',');
        sb.append("importedFrom");
        sb.append('=');
        sb.append(((this.importedFrom == null)?"<null>":this.importedFrom));
        sb.append(',');
        sb.append("noteableIid");
        sb.append('=');
        sb.append(((this.noteableIid == null)?"<null>":this.noteableIid));
        sb.append(',');
        sb.append("resolved");
        sb.append('=');
        sb.append(((this.resolved == null)?"<null>":this.resolved));
        sb.append(',');
        sb.append("resolvedBy");
        sb.append('=');
        sb.append(((this.resolvedBy == null)?"<null>":this.resolvedBy));
        sb.append(',');
        sb.append("resolvedAt");
        sb.append('=');
        sb.append(((this.resolvedAt == null)?"<null>":this.resolvedAt));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
