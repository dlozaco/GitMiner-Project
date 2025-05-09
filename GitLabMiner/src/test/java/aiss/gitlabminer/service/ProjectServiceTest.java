package aiss.gitlabminer.service;

import aiss.gitlabminer.model.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProjectServiceTest {

    @Autowired
    ProjectService projectService;

    @Test
    @DisplayName( "Test for getProject method")
    void getProject() {
        Project project = projectService.getProject("luggzz", "gitlabminer-test");

        assertNotNull(project, "Project is null");
        assertNotNull(project.getId(), "Project id is null");
        assertEquals("GitLabMiner-test", project.getName(), "Project name is incorrect");
        assertNotNull(project.getWebUrl(), "Project web url is null");

        assertEquals(4, project.getCommits().size(), "Project commits size is incorrect");
        assertEquals(2, project.getIssues().size(), "Project issues size is incorrect");

        project.getCommits().forEach(commit -> {
            assertNotNull(commit.getId(), "Commit id is null");
            assertNotNull(commit.getAuthoredDate(), "Commit authored date is null");
            assertNotNull(commit.getAuthorName(), "Commit author name is null");
            assertNotNull(commit.getWebUrl(), "Commit web url is null");
        });

        project.getIssues().forEach(issue -> {
            assertNotNull(issue.getId(), "Issue id is null");
            assertNotNull(issue.getAuthor(), "Issue author is null");
            assertNotNull(issue.getAuthor().getId(), "Issue author id is null");
            assertNotNull(issue.getAuthor().getUsername(), "Issue author username is null");

//            TO FIX: get comments with token
/*            issue.getComments().forEach(comment -> {
                assertNotNull(comment.getId(), "Comment id is null");
                assertNotNull(comment.getBody(), "Comment body is null");
                assertNotNull(comment.getCreatedAt(), "Comment created at is null");
                assertNotNull(comment.getAuthor(), "Comment author is null");
                assertNotNull(comment.getAuthor().getId(), "Comment author id is null");
                assertNotNull(comment.getAuthor().getUsername(), "Comment author username is null");
            });*/
        });

        System.out.println(project);
    }

    @Test
    @DisplayName("Test for postToGitMiner method")
    void postToGitMiner() {
        Project project = projectService.postToGitMiner("luggzz", "gitlabminer-test");

        assertNotNull(project, "Project is null");
        assertNotNull(project.getId(), "Project id is null");
        assertEquals("GitLabMiner-test", project.getName(), "Project name is incorrect");
        assertNotNull(project.getWebUrl(), "Project web url is null");

        assertEquals(4, project.getCommits().size(), "Project commits size is incorrect");
        assertEquals(2, project.getIssues().size(), "Project issues size is incorrect");

        project.getCommits().forEach(commit -> {
            assertNotNull(commit.getId(), "Commit id is null");
            assertNotNull(commit.getAuthoredDate(), "Commit authored date is null");
            assertNotNull(commit.getAuthorName(), "Commit author name is null");
            assertNotNull(commit.getWebUrl(), "Commit web url is null");
        });

        project.getIssues().forEach(issue -> {
            assertNotNull(issue.getId(), "Issue id is null");
            assertNotNull(issue.getAuthor(), "Issue author is null");
            assertNotNull(issue.getAuthor().getId(), "Issue author id is null");
            assertNotNull(issue.getAuthor().getUsername(), "Issue author username is null");

//            TO FIX: get comments with token
/*            issue.getComments().forEach(comment -> {
                assertNotNull(comment.getId(), "Comment id is null");
                assertNotNull(comment.getBody(), "Comment body is null");
                assertNotNull(comment.getCreatedAt(), "Comment created at is null");
                assertNotNull(comment.getAuthor(), "Comment author is null");
                assertNotNull(comment.getAuthor().getId(), "Comment author id is null");
                assertNotNull(comment.getAuthor().getUsername(), "Comment author username is null");
            });*/
        });

        System.out.println(project);
    }
}