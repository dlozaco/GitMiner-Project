package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.parsedModels.ParsedProject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProjectServiceTest {

    @Autowired
    ProjectService projectService;

    @Test
    @DisplayName("Find all projects")
    void getProject() {
        ParsedProject projects = projectService.getProject("gentlero", "bitbucket-api");
        System.out.println(projects);
    }
}