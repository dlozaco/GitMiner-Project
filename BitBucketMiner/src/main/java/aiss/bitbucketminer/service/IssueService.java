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

    public List<ParsedIssue> getAllIssues(String owner, String repo) {
        String uri = "https://api.bitbucket.org/2.0/repositories/" + owner + "/" + repo + "/issues";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<IssuePage> response = restTemplate.exchange(uri, HttpMethod.GET, request, IssuePage.class);

        List<Issue> issues = response.getBody().getIssues();
        return parseIssues(issues);
    }

    public List<ParsedIssue> parseIssues(List<Issue> issues) {
        List<ParsedIssue> parsedIssues = new ArrayList<>();
        for (Issue issue : issues) {
                    String id = issue.getId().toString();
                    String title = issue.getTitle();
                    String description = issue.getContent().getRaw();
                    String state = issue.getState();
                    String createdAt = issue.getCreatedOn();
                    String updatedAt = issue.getUpdatedOn();
                    String closedAt = null;
                    List<String> labels = new ArrayList<>();
                    Integer votes = issue.getVotes(); //???
                    ParsedUser author = parseUser(issue.getAuthor());
                    ParsedUser assignee = parseUser(issue.getAssignee());
                    List<ParsedComment> comments= commentService.getComments(issue.getLinks().getComments().getHref());

                    ParsedIssue parsedIssue = new ParsedIssue(id, title, description, state, createdAt, updatedAt,
                            closedAt, labels, votes, author, assignee, comments);
                    parsedIssues.add(parsedIssue);
        }
        return parsedIssues;
    }

    public ParsedUser parseUser(User user) {
        if (user == null) {
            return new ParsedUser("No ID", "No Username", "No Name", "No Avatar", "No URL");
        }

        return new ParsedUser(
                user.getUuid(),
                user.getDisplayName(),
                user.getNickname(),
                user.getLinks().getAvatar().getHref(),
                user.getLinks().getHtml().getHref()
        );
    }


}
