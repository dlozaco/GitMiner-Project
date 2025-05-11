package aiss.bitbucketminer.controller;

import aiss.bitbucketminer.model.parsedModels.ParsedProject;
import aiss.bitbucketminer.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/bitbucket")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/{workspace}/{repo}")
    public ParsedProject getProject(@PathVariable String workspace, @PathVariable String repo, @RequestParam(defaultValue = "5") Integer nCommits,
                                    @RequestParam(defaultValue = "5") Integer nIssues, @RequestParam(defaultValue = "2") Integer maxPages) {
        return projectService.getProject(workspace, repo, nCommits, nIssues, maxPages);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{workspace}/{repo}")
    public ParsedProject postToGitminer(@PathVariable String workspace, @PathVariable String repo, @RequestParam(defaultValue = "5") Integer nCommits,
                                        @RequestParam(defaultValue = "5") Integer nIssues, @RequestParam(defaultValue = "2") Integer maxPages) {
        return projectService.postProject(workspace, repo, nCommits, nIssues, maxPages);
    }
}
