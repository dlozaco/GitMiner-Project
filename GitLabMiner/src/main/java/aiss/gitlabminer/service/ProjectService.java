package aiss.gitlabminer.service;

import aiss.gitlabminer.model.*;

import org.springframework.beans.factory.annotation.Autowired;
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

    /*For the request of a project by its owner and name, the path https://gitlab.com/api/v4/projects/ only takes one
argument which is the full path owner/project, so, for the path to not process each one as a different argument, / must be
replaced by %2F in Postman requests, declared as a single parameter here*/

    public Project getProject(String owner, String name) {
        String param = owner + "/" + name;

//        Get project (single project, no need for pagination)
        Project project = restTemplate.getForObject("https://gitlab.com/api/v4/projects/{param}", Project.class, param);

//        Get commits and issues and set them in the project class
        List<Commit> allCommits = new ArrayList<>();
        List<Issue> allIssues = new ArrayList<>();
        List<IssueParsed> allIssuesParsed = new ArrayList<>();

        boolean hasNextPage = true;
        Integer i = 1;
        while (hasNextPage) {
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

        project.setCommits(allCommits);

        hasNextPage = true;
        i = 1;
        while (hasNextPage) {
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

        project.setIssues(allIssuesParsed);

//        Get comments for each issue and set them in the issues
//        Comments need authorization to get their info?
        for (Issue issue : allIssues) {
            List<Comment> allComments = new ArrayList<>();
            hasNextPage = true;
            i = 1;
            try {
                while (hasNextPage) {
                    Comment[] comments = restTemplate.exchange(
                            "https://gitlab.com/api/v4/projects/{projectId}/issues/{issueIid}/notes?page={page}&per_page=100",
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            Comment[].class, project.getId(), issue.getIid(), i).getBody();
                    allComments.addAll(Arrays.asList(comments));
                    HttpHeaders responseHeaders = restTemplate.exchange(
                            "https://gitlab.com/api/v4/projects/{projectId}/issues/{issueIid}/notes?page={page}&per_page=100",
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            Commit[].class, project.getId(), issue.getIid(), i).getHeaders();
                    if (responseHeaders.get("x-next-page").getFirst() != "") {
                        i++;
                    } else {
                        hasNextPage = false;
                    }
                }
            } catch (HttpClientErrorException.Unauthorized e) {
                System.out.println("Error getting comments for issue " + issue.getIid() + ": " + e.getMessage());
            } finally {
                issue.setComments(allComments);
            }
        }

        return project;
    }

    public Project postToGitMiner(String owner, String name) {
        Project createdProject = null;
        Project project = getProject(owner, name);
        try {
            createdProject = restTemplate.postForObject("http://localhost:8080/gitminer/projects", project, Project.class);
        } catch (RestClientException e) {
            System.out.println("Error posting project to GitMiner: " + e.getMessage());
        }
        return createdProject;
    }
}
