package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjectServiceTest {

    @Autowired
    ProjectService projectService;

    @Test
    @DisplayName("Find all projects")
    void getProjects() {
        List<Project> projects = projectService.getProjects("gentlero", "bitbucket-api");
        assertFalse(projects.isEmpty());
        System.out.println(projects);
    }
}