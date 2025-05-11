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

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
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

    public List<ParsedIssue> getAllIssues(String owner, String repo, Integer sinceIssue, Integer maxPages) {
        String url = baseUri + owner + "/" + repo + "/issues";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        Integer currentPage = 0;
        List<Issue> issues = new ArrayList<>();
        try {
            while (currentPage < maxPages) {
                HttpEntity<Issue[]> request = new HttpEntity<>(null, headers);
                System.out.println("Requesting: " + url);
                ResponseEntity<Issue[]> response = restTemplate.exchange(url, HttpMethod.GET, request, Issue[].class);
                if (response.getBody() != null) {
                    issues.addAll(Arrays.asList(response.getBody()));
                }
                url = getNextPageUrl(response.getHeaders());
                currentPage++;
            }
        } catch (Exception e) {
            System.out.println("Error fetching issues: " + e.getMessage());
        }
        List<ParsedIssue> allIssues = parseIssues(issues, sinceIssue);
        return allIssues;
    }

    private List<ParsedIssue> parseIssues(List<Issue> issues, Integer sinceIssue) {
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
            String cleanedDate = issue.getUpdatedAt().replace("Z", "");
            LocalDateTime localDateTime = LocalDateTime.parse(cleanedDate);
            if(ChronoUnit.DAYS.between(localDateTime, LocalDateTime.now()) < sinceIssue){
                res.add(newIssue);
            }
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

    public static String getNextPageUrl(HttpHeaders headers) {
        String result = null;

        // If there is no link header, return null
        List<String> linkHeader = headers.get("Link");
        if (linkHeader == null)
            return null;

        // If the header contains no links, return null
        String links = linkHeader.getFirst();
        if (links == null || links.isEmpty())
            return null;

        // Return the next page URL or null if none.
        for (String token : links.split(", ")) {
            if (token.endsWith("rel=\"next\"")) {
                // Found the next page. This should look something like
                // <https://api.github.com/repos?page=3&per_page=100>; rel="next"
                int idx = token.indexOf('>');
                result = token.substring(1, idx);
                break;
            }
        }

        return result;
    }
}
