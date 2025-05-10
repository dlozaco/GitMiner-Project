package aiss.githubminer.service;

import aiss.githubminer.model.issue.Issue;
import aiss.githubminer.model.issue.Label;
import aiss.githubminer.model.issue.User;
import aiss.githubminer.parsedmodel.ParsedIssue;
import aiss.githubminer.parsedmodel.ParsedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class IssueService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CommentService commentService;

    @Value("${github.api.token}")
    private String token;

    String baseUri = "https://api.github.com/repos/";

    public List<ParsedIssue> getAllIssues(String owner, String repo){
        String url = baseUri + owner + "/" + repo + "/issues";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<Issue[]> request = new HttpEntity<>(null, headers);
        ResponseEntity<Issue[]> response = restTemplate.exchange(url, HttpMethod.GET, request, Issue[].class);
        Issue[] issues = response.getBody();
        List<ParsedIssue> allIssues = parseIssues(issues, owner, repo);
        return allIssues;
    }

    private List<ParsedIssue> parseIssues(Issue[] issues, String owner, String repo) {
        List<ParsedIssue> res = new ArrayList<>();
        for(Issue issue:issues){
            List<String> labels = new ArrayList<>();
            for(Label label:issue.getLabels()){
                labels.add(label.getName());
            }
            Integer votes = issue.getReactions().getTotalCount();
            ParsedIssue newIssue = new ParsedIssue(
                    String.valueOf(issue.getId()),
                    issue.getTitle(),
                    issue.getBody(),
                    issue.getState(),
                    issue.getCreatedAt(),
                    issue.getUpdatedAt(),
                    issue.getClosedAt(),
                    labels,
                    parseUser(issue.getUser()),
                    votes,
                    parseUser(issue.getAsignee()),
                    commentService.getAllComments(issue.getComments_url())
            );
            res.add(newIssue);
        }

        return res;

    }

    private ParsedUser parseUser(User user) {
        if(user == null){
            return null;
        }
        return new ParsedUser(
                user.getId(),
                user.getLogin(),
                user.getLogin().replaceAll("[^a-zA-Z0-9]", ""),
                user.getAvatarUrl(),
                user.getHtmlUrl()
        );
    }
}
