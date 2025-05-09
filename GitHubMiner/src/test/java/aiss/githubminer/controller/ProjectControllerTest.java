package aiss.githubminer.controller;

import aiss.githubminer.model.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProjectControllerTest {

    @Autowired
    ProjectController projectController;

    @Test
    @DisplayName("Get project")
    void testGetProject(){
        String owner = "dlozaco";
        String repo = "tetrio-comparator";

        Project project = projectController.getProject(owner, repo);
        assertNotNull(project, "The project is null");
        System.out.println(project);
    }
}