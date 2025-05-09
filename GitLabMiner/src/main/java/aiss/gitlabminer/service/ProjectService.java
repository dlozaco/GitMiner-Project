package aiss.gitlabminer.service;

import aiss.gitlabminer.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    RestTemplate restTemplate;

    /*For the request of a project by its owner and name, the path https://gitlab.com/api/v4/projects/ only takes one
argument which is the full path owner/project, so, for the path to not process each one as a different argument, / must be
replaced by %2F in Postman requests, declared as a single parameter here*/

    public Project getProject(String owner, String name) {
        String param = owner + "/" + name;

//        TO FIX: pagination for commits, issues and comments?

//        Get project
        Project project = restTemplate.getForObject("https://gitlab.com/api/v4/projects/{param}", Project.class, param);

//        Get commits and issues and set them in the project class
        Commit[] commits = restTemplate.getForObject("https://gitlab.com/api/v4/projects/{projectId}/repository/commits", Commit[].class, project.getId());
        project.setCommits(List.of(commits));
        Issue[] issues = restTemplate.getForObject("https://gitlab.com/api/v4/projects/{projectId}/issues", Issue[].class, project.getId());
        project.setIssues(List.of(issues));

//        Get comments for each issue and set them in the issues
//        Comments need authorization to get their info?
        for (Issue issue : issues) {
            try {
            Comment[] comments = restTemplate.getForObject("https://gitlab.com/api/v4/projects/{projectId}/issues/{issueIid}/notes", Comment[].class, project.getId(), issue.getIid());
            issue.setComments(List.of(comments));
            } catch (HttpClientErrorException.Unauthorized e) {
                System.out.println("Error getting comments for issue " + issue.getIid() + ": " + e.getMessage());
            }
        }

        return project;
    }
}
