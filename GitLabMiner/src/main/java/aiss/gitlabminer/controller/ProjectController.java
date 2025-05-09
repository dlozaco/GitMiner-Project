package aiss.gitlabminer.controller;

import aiss.gitlabminer.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gitlab/api/commits")
public class ProjectController {

    @Autowired
    ProjectService projectService;

}
