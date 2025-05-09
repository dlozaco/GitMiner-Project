package aiss.gitlabminer.service;

import aiss.gitlabminer.model.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProjectServiceTest {

    @Autowired
    ProjectService projectService;

    @Test
    @DisplayName( "Test for getProject method")
    void getProject() {
        Project project = projectService.getProject("gitlab-org", "gitlab");
/*        System.out.println(project);
        System.out.println(project.getCommits());*/
//        System.out.println(project.getIssues().stream().filter(issue -> issue.getComments().size()>0).findFirst().get().getComments());
        System.out.println(project);
    }
}