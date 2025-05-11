package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.commit.Commit;
import aiss.bitbucketminer.model.issue.Issue;
import aiss.bitbucketminer.model.parsedModels.ParsedCommit;
import aiss.bitbucketminer.model.parsedModels.ParsedIssue;
import aiss.bitbucketminer.model.project.Project;
import aiss.bitbucketminer.model.project.Repositories;
import aiss.bitbucketminer.model.project.Workspace;
import aiss.bitbucketminer.model.parsedModels.ParsedProject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
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

    public ParsedProject getProject(String workspace, String repo, Integer nCommits, Integer nIssues, Integer maxPages) {
        String url = "https://api.bitbucket.org/2.0/repositories/" + workspace + "/" + repo;
        Project project = restTemplate.getForObject(url, Project.class);

        String id = project.getUuid();
        String name = project.getName();
        String web_url = project.getLinks().getSelf().getHref();
        List<ParsedCommit> commits = commitService.getCommits(url + "/commits", nCommits, maxPages);
        List<ParsedIssue> issues = issueService.getAllIssues(url + "/issues", nIssues, maxPages);
        return new ParsedProject(id, name, web_url, commits, issues);
    }

    public ParsedProject postProject(@PathVariable String workspace, @PathVariable String repo, Integer nCommits, Integer nIssues, Integer maxPages) {
        ParsedProject newProject = null;
        ParsedProject project = getProject(workspace, repo, nCommits, nIssues, maxPages);
        try {
            newProject = restTemplate.postForObject("http://localhost:8080/gitminer/projects", project, ParsedProject.class);
        } catch (Exception e) {
            System.err.println(e);
        }
        return newProject;
    }




}
