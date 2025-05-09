package aiss.gitminer.model.controller;

import aiss.gitminer.controller.IssueController;
import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Issue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IssueControllerTest {
    @Autowired
    IssueController issueController;

    @Test
    @DisplayName("Get all Issues")
    void getAllIssues() {
        assertNotNull(issueController.getAllIssues(null, null));
        List<Issue> issues = issueController.getAllIssues(null, null);
        System.out.println(issues);
    }
    @Test
    @DisplayName("Get issue by ID")
    void getIssueById() {
        assertNotNull(issueController.getIssueById("1"), "Issue not found");
        Issue issue = issueController.getIssueById("1");
        System.out.println(issue);
    }

    @Test
    void getAllCommentsByIssueId() {
        assertNotNull(issueController.getAllCommentsByIssueId("1"), "Issue not found");
        List<Comment> comments = issueController.getAllCommentsByIssueId("1");
        System.out.println(comments);
    }
}