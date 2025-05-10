package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.project.Project;
import aiss.bitbucketminer.model.project.Repositories;
import aiss.bitbucketminer.model.project.Workspace;
import aiss.bitbucketminer.model.parsedModels.ParsedProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

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
        ResponseEntity<Project> response = restTemplate.exchange(url, HttpMethod.GET, request, Project.class);
        Project project = response.getBody();
        List<ParsedCommit> commits = commitService.getAllCommits(owner, repo);
        List<ParsedIssue> issues = issueService.getAllIssues(owner, repo);

        ParsedProject parsedProject = new ParsedProject(
                String.valueOf(project.getId()),
                project.getName(),
                project.getHtmlUrl(),
                commits,
                issues
        );

        return parsedProject;
    }


}
