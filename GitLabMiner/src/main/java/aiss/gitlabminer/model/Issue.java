package aiss.gitlabminer.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Issue {

//    id in GitMiner model
    @JsonProperty("id")
    private Integer id;

//    needed to get the comments  -> GET /projects/:id/issues/:issue_iid/notes
    @JsonProperty("iid")
    private Integer iid;

//    Not necessary to add because they're linked?
    @JsonProperty("project_id")
    private Integer projectId;

//    title in GitMiner model
    @JsonProperty("title")
    private String title;

//    description in GitMiner model
    @JsonProperty("description")
    private String description;

//    state in GitMiner model
    @JsonProperty("state")
    private String state;

//    created_at in GitMiner model
    @JsonProperty("created_at")
    private String createdAt;

//    updated_at in GitMiner model
    @JsonProperty("updated_at")
    private String updatedAt;

//    closed_at in GitMiner model
    @JsonProperty("closed_at")
    private Object closedAt;

    @JsonProperty("closed_by")
    private Object closedBy;

//    labels in GitMiner model
    @JsonProperty("labels")
    private List<String> labels;

    @JsonProperty("milestone")
    private Object milestone;

    @JsonProperty("assignees")
    private List<Object> assignees;

//    author_id in GitMiner model
    @JsonProperty("author")
    private User author;

    @JsonProperty("type")
    private String type;

//    assignee_id in GitMiner model
    @JsonProperty("assignee")
    private User assignee;
    @JsonProperty("user_notes_count")
    private Integer userNotesCount;
    @JsonProperty("merge_requests_count")
    private Integer mergeRequestsCount;

//    votes in GitMiner model
    @JsonProperty("upvotes")
    private Integer upvotes;

//    votes in GitMiner model
    @JsonProperty("downvotes")
    private Integer downvotes;
    @JsonProperty("due_date")
    private Object dueDate;
    @JsonProperty("confidential")
    private Boolean confidential;
    @JsonProperty("discussion_locked")
    private Object discussionLocked;
    @JsonProperty("issue_type")
    private String issueType;
    @JsonProperty("web_url")
    private String webUrl;
    @JsonProperty("blocking_issues_count")
    private Integer blockingIssuesCount;
    @JsonProperty("has_tasks")
    private Boolean hasTasks;
    @JsonProperty("task_status")
    private String taskStatus;

    @JsonProperty("severity")
    private String severity;
    @JsonProperty("moved_to_id")
    private Object movedToId;
    @JsonProperty("imported")
    private Boolean imported;
    @JsonProperty("imported_from")
    private String importedFrom;
    @JsonProperty("service_desk_reply_to")
    private Object serviceDeskReplyTo;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("iid")
    public Integer getIid() {
        return iid;
    }

    @JsonProperty("iid")
    public void setIid(Integer iid) {
        this.iid = iid;
    }

    @JsonProperty("project_id")
    public Integer getProjectId() {
        return projectId;
    }

    @JsonProperty("project_id")
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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

    @JsonProperty("closed_by")
    public Object getClosedBy() {
        return closedBy;
    }

    @JsonProperty("closed_by")
    public void setClosedBy(Object closedBy) {
        this.closedBy = closedBy;
    }

    @JsonProperty("labels")
    public List<String> getLabels() {
        return labels;
    }

    @JsonProperty("labels")
    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    @JsonProperty("milestone")
    public Object getMilestone() {
        return milestone;
    }

    @JsonProperty("milestone")
    public void setMilestone(Object milestone) {
        this.milestone = milestone;
    }

    @JsonProperty("assignees")
    public List<Object> getAssignees() {
        return assignees;
    }

    @JsonProperty("assignees")
    public void setAssignees(List<Object> assignees) {
        this.assignees = assignees;
    }

    @JsonProperty("author")
    public User getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(User author) {
        this.author = author;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("assignee")
    public User getAssignee() {
        return assignee;
    }

    @JsonProperty("assignee")
    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    @JsonProperty("user_notes_count")
    public Integer getUserNotesCount() {
        return userNotesCount;
    }

    @JsonProperty("user_notes_count")
    public void setUserNotesCount(Integer userNotesCount) {
        this.userNotesCount = userNotesCount;
    }

    @JsonProperty("merge_requests_count")
    public Integer getMergeRequestsCount() {
        return mergeRequestsCount;
    }

    @JsonProperty("merge_requests_count")
    public void setMergeRequestsCount(Integer mergeRequestsCount) {
        this.mergeRequestsCount = mergeRequestsCount;
    }

    @JsonProperty("upvotes")
    public Integer getUpvotes() {
        return upvotes;
    }

    @JsonProperty("upvotes")
    public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
    }

    @JsonProperty("downvotes")
    public Integer getDownvotes() {
        return downvotes;
    }

    @JsonProperty("downvotes")
    public void setDownvotes(Integer downvotes) {
        this.downvotes = downvotes;
    }

    @JsonProperty("due_date")
    public Object getDueDate() {
        return dueDate;
    }

    @JsonProperty("due_date")
    public void setDueDate(Object dueDate) {
        this.dueDate = dueDate;
    }

    @JsonProperty("confidential")
    public Boolean getConfidential() {
        return confidential;
    }

    @JsonProperty("confidential")
    public void setConfidential(Boolean confidential) {
        this.confidential = confidential;
    }

    @JsonProperty("discussion_locked")
    public Object getDiscussionLocked() {
        return discussionLocked;
    }

    @JsonProperty("discussion_locked")
    public void setDiscussionLocked(Object discussionLocked) {
        this.discussionLocked = discussionLocked;
    }

    @JsonProperty("issue_type")
    public String getIssueType() {
        return issueType;
    }

    @JsonProperty("issue_type")
    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    @JsonProperty("web_url")
    public String getWebUrl() {
        return webUrl;
    }

    @JsonProperty("web_url")
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    @JsonProperty("blocking_issues_count")
    public Integer getBlockingIssuesCount() {
        return blockingIssuesCount;
    }

    @JsonProperty("blocking_issues_count")
    public void setBlockingIssuesCount(Integer blockingIssuesCount) {
        this.blockingIssuesCount = blockingIssuesCount;
    }

    @JsonProperty("has_tasks")
    public Boolean getHasTasks() {
        return hasTasks;
    }

    @JsonProperty("has_tasks")
    public void setHasTasks(Boolean hasTasks) {
        this.hasTasks = hasTasks;
    }

    @JsonProperty("task_status")
    public String getTaskStatus() {
        return taskStatus;
    }

    @JsonProperty("task_status")
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    @JsonProperty("severity")
    public String getSeverity() {
        return severity;
    }

    @JsonProperty("severity")
    public void setSeverity(String severity) {
        this.severity = severity;
    }

    @JsonProperty("moved_to_id")
    public Object getMovedToId() {
        return movedToId;
    }

    @JsonProperty("moved_to_id")
    public void setMovedToId(Object movedToId) {
        this.movedToId = movedToId;
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

    @JsonProperty("service_desk_reply_to")
    public Object getServiceDeskReplyTo() {
        return serviceDeskReplyTo;
    }

    @JsonProperty("service_desk_reply_to")
    public void setServiceDeskReplyTo(Object serviceDeskReplyTo) {
        this.serviceDeskReplyTo = serviceDeskReplyTo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Project.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("iid");
        sb.append('=');
        sb.append(((this.iid == null)?"<null>":this.iid));
        sb.append(',');
        sb.append("projectId");
        sb.append('=');
        sb.append(((this.projectId == null)?"<null>":this.projectId));
        sb.append(',');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null)?"<null>":this.title));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("state");
        sb.append('=');
        sb.append(((this.state == null)?"<null>":this.state));
        sb.append(',');
        sb.append("createdAt");
        sb.append('=');
        sb.append(((this.createdAt == null)?"<null>":this.createdAt));
        sb.append(',');
        sb.append("updatedAt");
        sb.append('=');
        sb.append(((this.updatedAt == null)?"<null>":this.updatedAt));
        sb.append(',');
        sb.append("closedAt");
        sb.append('=');
        sb.append(((this.closedAt == null)?"<null>":this.closedAt));
        sb.append(',');
        sb.append("closedBy");
        sb.append('=');
        sb.append(((this.closedBy == null)?"<null>":this.closedBy));
        sb.append(',');
        sb.append("labels");
        sb.append('=');
        sb.append(((this.labels == null)?"<null>":this.labels));
        sb.append(',');
        sb.append("milestone");
        sb.append('=');
        sb.append(((this.milestone == null)?"<null>":this.milestone));
        sb.append(',');
        sb.append("assignees");
        sb.append('=');
        sb.append(((this.assignees == null)?"<null>":this.assignees));
        sb.append(',');
        sb.append("author");
        sb.append('=');
        sb.append(((this.author == null)?"<null>":this.author));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("assignee");
        sb.append('=');
        sb.append(((this.assignee == null)?"<null>":this.assignee));
        sb.append(',');
        sb.append("userNotesCount");
        sb.append('=');
        sb.append(((this.userNotesCount == null)?"<null>":this.userNotesCount));
        sb.append(',');
        sb.append("mergeRequestsCount");
        sb.append('=');
        sb.append(((this.mergeRequestsCount == null)?"<null>":this.mergeRequestsCount));
        sb.append(',');
        sb.append("upvotes");
        sb.append('=');
        sb.append(((this.upvotes == null)?"<null>":this.upvotes));
        sb.append(',');
        sb.append("downvotes");
        sb.append('=');
        sb.append(((this.downvotes == null)?"<null>":this.downvotes));
        sb.append(',');
        sb.append("dueDate");
        sb.append('=');
        sb.append(((this.dueDate == null)?"<null>":this.dueDate));
        sb.append(',');
        sb.append("confidential");
        sb.append('=');
        sb.append(((this.confidential == null)?"<null>":this.confidential));
        sb.append(',');
        sb.append("discussionLocked");
        sb.append('=');
        sb.append(((this.discussionLocked == null)?"<null>":this.discussionLocked));
        sb.append(',');
        sb.append("issueType");
        sb.append('=');
        sb.append(((this.issueType == null)?"<null>":this.issueType));
        sb.append(',');
        sb.append("webUrl");
        sb.append('=');
        sb.append(((this.webUrl == null)?"<null>":this.webUrl));
        sb.append(',');
        sb.append("blockingIssuesCount");
        sb.append('=');
        sb.append(((this.blockingIssuesCount == null)?"<null>":this.blockingIssuesCount));
        sb.append(',');
        sb.append("hasTasks");
        sb.append('=');
        sb.append(((this.hasTasks == null)?"<null>":this.hasTasks));
        sb.append(',');
        sb.append("taskStatus");
        sb.append('=');
        sb.append(((this.taskStatus == null)?"<null>":this.taskStatus));
        sb.append(',');
        sb.append("severity");
        sb.append('=');
        sb.append(((this.severity == null)?"<null>":this.severity));
        sb.append(',');
        sb.append("movedToId");
        sb.append('=');
        sb.append(((this.movedToId == null)?"<null>":this.movedToId));
        sb.append(',');
        sb.append("imported");
        sb.append('=');
        sb.append(((this.imported == null)?"<null>":this.imported));
        sb.append(',');
        sb.append("importedFrom");
        sb.append('=');
        sb.append(((this.importedFrom == null)?"<null>":this.importedFrom));
        sb.append(',');
        sb.append("serviceDeskReplyTo");
        sb.append('=');
        sb.append(((this.serviceDeskReplyTo == null)?"<null>":this.serviceDeskReplyTo));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
