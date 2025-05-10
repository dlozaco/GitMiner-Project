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

    @Value("${gitlab.token}")
    private String token;

    /*For the request of a project by its owner and name, the path https://gitlab.com/api/v4/projects/ only takes one
argument which is the full path owner/project, so, for the path to not process each one as a different argument, / must be
replaced by %2F in Postman requests, declared as a single parameter here*/

    public Project getProject(String owner, String name, Integer nCommits, Integer nIssues) {
        String param = owner + "/" + name;

//        Get project (single project, no need for pagination)
        Project project = restTemplate.getForObject("https://gitlab.com/api/v4/projects/{param}", Project.class, param);

//        Get commits and issues and set them in the project class
        List<Commit> allCommits = new ArrayList<>();
        List<Issue> allIssues = new ArrayList<>();
        List<IssueParsed> allIssuesParsed = new ArrayList<>();

        boolean hasNextPage = true;
        Integer i = 1;
        while (hasNextPage && allCommits.size() < nCommits) {
            Commit[] commits = restTemplate.exchange(
                    "https://gitlab.com/api/v4/projects/{projectId}/repository/commits?page={page}&per_page=100",
                    HttpMethod.GET,
                    new HttpEntity<>(new HttpHeaders()),
                    Commit[].class, project.getId(), i).getBody();
            allCommits.addAll(Arrays.asList(commits));
            HttpHeaders responseHeaders = restTemplate.exchange(
                    "https://gitlab.com/api/v4/projects/{projectId}/repository/commits?page={page}&per_page=1",
                    HttpMethod.GET,
                    new HttpEntity<>(new HttpHeaders()),
                    Commit[].class, project.getId(), i).getHeaders();
            if (responseHeaders.get("x-next-page").getFirst() != "") {
                i++;
            } else {
                hasNextPage = false;
            }
        }

        if (allCommits.size() < nCommits) {
            project.setCommits(allCommits);
        } else {
            project.setCommits(allCommits.subList(0, nCommits));
        }

        hasNextPage = true;
        i = 1;
        while (hasNextPage && allIssues.size() < nIssues) {
            Issue[] issues = restTemplate.exchange(
                    "https://gitlab.com/api/v4/projects/{projectId}/issues?page={page}&per_page=100",
                    HttpMethod.GET,
                    new HttpEntity<>(new HttpHeaders()),
                    Issue[].class, project.getId(), i).getBody();
            allIssues.addAll(Arrays.asList(issues));
            HttpHeaders responseHeaders = restTemplate.exchange(
                    "https://gitlab.com/api/v4/projects/{projectId}/issues?page={page}&per_page=1",
                    HttpMethod.GET,
                    new HttpEntity<>(new HttpHeaders()),
                    Commit[].class, project.getId(), i).getHeaders();
            if (responseHeaders.get("x-next-page").getFirst() != "") {
                i++;
            } else {
                hasNextPage = false;
            }
        }

//        parse issues to GitMiner models
        for (Issue issue : allIssues) {
            IssueParsed parsedIssue = new IssueParsed(issue.getId(), issue.getTitle(), issue.getDescription(),
                    issue.getState(), issue.getCreatedAt(), issue.getUpdatedAt(), issue.getClosedAt(), issue.getLabels(),
                    issue.getAuthor(), issue.getAssignee(), issue.getVotes(), issue.getComments());
            allIssuesParsed.add(parsedIssue);
        }
        if (allIssuesParsed.size() < nIssues) {
            project.setIssues(allIssuesParsed);
        } else {
            project.setIssues(allIssuesParsed.subList(0, nIssues));
        }

//        Get comments for each issue and set them in the issues
//        Comments need authorization to get their info
        for (Issue issue : allIssues.subList(0, project.getIssues().size())) {
            List<Comment> allComments = new ArrayList<>();
            hasNextPage = true;
            i = 1;
            try {
                while (hasNextPage) {
                    Comment[] comments = restTemplate.exchange(
                            "https://gitlab.com/api/v4/projects/{projectId}/issues/{issueIid}/notes?page={page}&per_page=100&private_token={token}",
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            Comment[].class, project.getId(), issue.getIid(), i, token).getBody();
                    allComments.addAll(Arrays.asList(comments));
                    HttpHeaders responseHeaders = restTemplate.exchange(
                            "https://gitlab.com/api/v4/projects/{projectId}/issues/{issueIid}/notes?page={page}&per_page=100&private_token={token}",
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            Commit[].class, project.getId(), issue.getIid(), i, token).getHeaders();
                    if (responseHeaders.get("x-next-page").getFirst() != "") {
                        i++;
                    } else {
                        hasNextPage = false;
                    }
                }
            } catch (HttpClientErrorException.Unauthorized e) {
                System.out.println("Error getting comments for issue " + issue.getIid() + ": " + e.getMessage());
            } finally {
                project.getIssues().get(allIssues.indexOf(issue)).setComments(allComments);
            }
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
