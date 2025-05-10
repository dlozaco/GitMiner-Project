package aiss.githubminer.service;

import aiss.githubminer.parsedmodel.ParsedIssue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
class IssueServiceTest {

    @Autowired
    IssueService issueService;

    @Test
    void testGetAllIssues() {
        List<ParsedIssue> issues = issueService.getAllIssues("dlozaco", "tetrio-comparator");
        assertNotNull(issues, "The project is null");
        assertFalse(issues.isEmpty(), "The project is empty");
        System.out.println(issues);

    }
}