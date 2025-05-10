package aiss.gitlabminer.service;

import aiss.gitlabminer.model.IssueParsed;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IssueServiceTest {

    @Autowired
    IssueService issueService;

    @Test
    @DisplayName("Test for getAllIssues method")
    void getAllIssues() {
        List<IssueParsed> issues = issueService.getAllIssues("69720129");
        assertEquals(2, issues.size(), "Project issues size is incorrect");
        issues.forEach(issue -> {
            assertNotNull(issue.getId(), "Issue id is null");
            assertNotNull(issue.getAuthor(), "Issue author is null");
            assertNotNull(issue.getAuthor().getId(), "Issue author id is null");
            assertNotNull(issue.getAuthor().getUsername(), "Issue author username is null");

            issue.getComments().forEach(comment -> {
                assertNotNull(comment.getId(), "Comment id is null");
                assertNotNull(comment.getBody(), "Comment body is null");
                assertNotNull(comment.getCreatedAt(), "Comment created at is null");
                assertNotNull(comment.getAuthor(), "Comment author is null");
                assertNotNull(comment.getAuthor().getId(), "Comment author id is null");
                assertNotNull(comment.getAuthor().getUsername(), "Comment author username is null");
            });
        });
    }
}