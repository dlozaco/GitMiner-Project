package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.comment.Comment;
import aiss.bitbucketminer.model.issue.Issue;
import aiss.bitbucketminer.model.issue.IssuePage;
import aiss.bitbucketminer.model.issue.User;
import aiss.bitbucketminer.model.parsedModels.ParsedComment;
import aiss.bitbucketminer.model.parsedModels.ParsedIssue;
import aiss.bitbucketminer.model.parsedModels.ParsedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class IssueService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    public List<ParsedIssue> getAllIssues(String uri, Integer nIssues, Integer maxPages) {
        IssuePage issues = restTemplate.getForObject(uri, IssuePage.class);
        return parseIssues(issues, nIssues, maxPages);
    }

    public List<ParsedIssue> parseIssues(IssuePage issues, Integer nIssues, Integer maxPages) {
        List<ParsedIssue> parsedIssues = new ArrayList<>();
        for (Issue issue : issues.getIssues()) {
            String id = issue.getId().toString();
            String title = issue.getTitle();
            String description = issue.getContent().getRaw();
            String state = issue.getState();
            String createdAt = issue.getCreatedOn();
            String updatedAt = issue.getUpdatedOn();
            String closedAt = null;
            List<String> labels = new ArrayList<>();
            Integer votes = issue.getVotes(); //???
            ParsedUser author = userService.parseUser(issue.getAuthor());
            ParsedUser assignee = userService.parseUser(issue.getAssignee());
            List<ParsedComment> comments= commentService.getComments(issue.getLinks().getComments().getHref());

            ParsedIssue parsedIssue = new ParsedIssue(id, title, description, state, createdAt, updatedAt,
                    closedAt, labels, votes, author, assignee, comments);
            parsedIssues.add(parsedIssue);

            if (parsedIssues.size() == nIssues) {
                break;
            }
        }
        if (issues.getNext() != null && issues.getPage() < maxPages) {
            parsedIssues.addAll(getAllIssues(issues.getNext(), nIssues, maxPages));
        }
        return parsedIssues;
    }



}
