package aiss.gitlabminer.service;

import aiss.gitlabminer.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ProjectService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CommitService commitService;

    @Autowired
    IssueService issueService;

    /*For the request of a project by its owner and name, the path https://gitlab.com/api/v4/projects/ only takes one
argument which is the full path owner/project, so, for the path to not process each one as a different argument, / must be
replaced by %2F in Postman requests, declared as a single parameter here*/

    public Project getProject(String owner, String name, Integer nCommits, Integer nIssues) {
        String param = owner + "/" + name;

//        Get project (single project, no need for pagination)
        Project project = restTemplate.getForObject("https://gitlab.com/api/v4/projects/{param}", Project.class, param);

//        Get commits and set them according to the number of commits requested
        List<Commit> allCommits = commitService.getAllCommits(project.getId());
        if (allCommits.size() < nCommits) {
            project.setCommits(allCommits);
        } else {
            project.setCommits(allCommits.subList(0, nCommits));
        }

//        Get issues and set them according to the number of issues requested
        List<IssueParsed> allIssuesParsed = issueService.getAllIssues(project.getId());
        if (allIssuesParsed.size() < nIssues) {
            project.setIssues(allIssuesParsed);
        } else {
            project.setIssues(allIssuesParsed.subList(0, nIssues));
        }

        return project;
    }

    public Project postToGitMiner(String owner, String name, Integer nCommits, Integer nIssues) {
        Project createdProject = null;
        Project project = getProject(owner, name, nCommits, nIssues);
        try {
            createdProject = restTemplate.postForObject("http://localhost:8080/gitminer/projects", project, Project.class);
        } catch (RestClientException e) {
            System.out.println("Error posting project to GitMiner: " + e.getMessage());
        }
        return createdProject;
    }
}
