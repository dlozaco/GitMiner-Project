package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.issue.Issue;
import aiss.bitbucketminer.model.parsedModels.ParsedCommit;
import aiss.bitbucketminer.model.parsedModels.ParsedIssue;
import aiss.bitbucketminer.model.project.Project;
import aiss.bitbucketminer.model.project.Repositories;
import aiss.bitbucketminer.model.project.Workspace;
import aiss.bitbucketminer.model.parsedModels.ParsedProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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

    public ParsedProject getProject(String owner, String repo){
        String url = "https://api.bitbucket.org/2.0/repositories/" + owner + "/" + repo;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<Project> request = new HttpEntity<>(null, headers);

        ResponseEntity<Project> response = restTemplate.exchange(url, HttpMethod.GET, request, Project.class);

        Project project = response.getBody();
        List<ParsedCommit> commits = commitService.getCommits(owner, repo);
        List<ParsedIssue> issues = issueService.getAllIssues(owner, repo);

        ParsedProject parsedProject = new ParsedProject(
                project.getUuid(),
                project.getName(),
                project.getLinks().getHtml().getHref(),
                commits,
                issues

        );
        return parsedProject;
    }




}
