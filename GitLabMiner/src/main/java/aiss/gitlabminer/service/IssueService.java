package aiss.gitlabminer.service;

import aiss.gitlabminer.model.Commit;
import aiss.gitlabminer.model.Issue;
import aiss.gitlabminer.model.IssueParsed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class IssueService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CommentService commentService;

    public List<IssueParsed> getAllIssues(String projectId) {
        List<Issue> allIssues = new ArrayList<>();
        List<IssueParsed> allIssuesParsed = new ArrayList<>();

        boolean hasNextPage = true;
        Integer i = 1;
        while (hasNextPage) {
//            Gets issues of a page
            Issue[] issues = restTemplate.exchange(
                    "https://gitlab.com/api/v4/projects/{projectId}/issues?page={page}&per_page=100",
                    HttpMethod.GET,
                    new HttpEntity<>(new HttpHeaders()),
                    Issue[].class, projectId, i).getBody();

            allIssues.addAll(Arrays.asList(issues));

//            Gets the headers of the response to check if there is a next page
            HttpHeaders responseHeaders = restTemplate.exchange(
                    "https://gitlab.com/api/v4/projects/{projectId}/issues?page={page}&per_page=1",
                    HttpMethod.GET,
                    new HttpEntity<>(new HttpHeaders()),
                    Commit[].class, projectId, i).getHeaders();

//            If there is a next page, increment the page number and continue retrieving issues
            if (responseHeaders.get("x-next-page").getFirst() != "") {
                i++;
            } else {
                hasNextPage = false;
            }
        }

//            Parse issues to GitMiner model
        for (Issue issue : allIssues) {
            IssueParsed parsedIssue = new IssueParsed(issue.getId(), issue.getTitle(), issue.getDescription(),
                    issue.getState(), issue.getCreatedAt(), issue.getUpdatedAt(), issue.getClosedAt(), issue.getLabels(),
                    issue.getAuthor(), issue.getAssignee(), issue.getVotes(), issue.getComments());
            allIssuesParsed.add(parsedIssue);
        }

//        Get the comments of each issue
        for (Issue issue : allIssues) {
            allIssuesParsed.get(allIssues.indexOf(issue)).setComments(
                    commentService.getAllComments(projectId, issue.getIid()));
        }

        return allIssuesParsed;
    }

}
