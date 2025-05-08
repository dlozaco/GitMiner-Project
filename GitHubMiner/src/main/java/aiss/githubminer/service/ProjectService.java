package aiss.githubminer.service;

import aiss.githubminer.model.Project;
import aiss.githubminer.model.commit.Commit;
import aiss.githubminer.model.issue.Issue;
import aiss.githubminer.parsedmodel.ParsedCommit;
import aiss.githubminer.parsedmodel.ParsedProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CommitService commitService;

    @Autowired
    IssueService issueService;

    @Value("${github.api.token}")
    private String token;

    String baseUri = "https://api.github.com/repos/";

    public ParsedProject getProject(String owner, String repo){
        String url = baseUri + owner + "/" + repo;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + token);

        HttpEntity<Project> request = new HttpEntity<>(null, headers);
        ResponseEntity<Project> response = restTemplate.exchange(url, HttpMethod.GET, request, Project.class);
        Project project = response.getBody();
        List<ParsedCommit> commits = commitService.getAllCommits(owner, repo);
        Issue[] issues = issueService.getAllIssues(owner, repo);

        ParsedProject parsedProject = new ParsedProject(
                project.getId(),
                project.getName(),
                project.getHtmlUrl(),
                commits,
                Arrays.stream(issues).toList()
        );

        return parsedProject;
    }
}
