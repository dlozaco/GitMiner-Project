package aiss.githubminer.controller;

import aiss.githubminer.model.Project;
import aiss.githubminer.parsedmodel.ParsedProject;
import aiss.githubminer.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/github")
public class ProjectController {

    @Autowired
    ProjectService projectService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{owner}/{repo}")
    public ParsedProject getProject(@PathVariable(value = "owner") String owner,
                                    @PathVariable(value = "repo") String repo,
                                    @RequestParam(required = false, defaultValue = "2") Integer sinceCommits,
                                    @RequestParam(required = false, defaultValue = "20") Integer sinceIssues,
                                    @RequestParam(required = false, defaultValue = "2") Integer maxPages){
        return projectService.getProject(owner, repo, sinceCommits, sinceIssues, maxPages);
    }

    @PostMapping("/{owner}/{repo}")
    public ParsedProject postToGitminer(@PathVariable (value="owner") String owner,
                                        @PathVariable (value= "repo") String repo,
                                        @RequestParam(required = false, defaultValue = "2") Integer sinceCommits,
                                        @RequestParam(required = false, defaultValue = "20") Integer sinceIssues,
                                        @RequestParam(required = false, defaultValue = "2") Integer maxPages){
        return projectService.postProjectToGitminer(owner,repo, sinceCommits, sinceIssues, maxPages);
    }


}
