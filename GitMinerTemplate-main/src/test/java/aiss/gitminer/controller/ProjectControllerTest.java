package aiss.gitminer.controller;


import aiss.gitminer.model.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProjectControllerTest {

    @Autowired
    ProjectController projectController;

    @Test
    @DisplayName("Get all repositores")
    void testFindAllProjects(){
        List<Project> projects = projectController.findAllProjects();
        assertFalse(projects.isEmpty(), "The list of repositories is empty");
        System.out.println(projects);
    }

    @Test
    @DisplayName("Create a new project")
    void testCreateProject(){
        Project project = new Project("tetrio", "hola.com");
        Project createdProject = projectController.createProject(project);

    }

}
