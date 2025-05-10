package aiss.githubminer.service;

import aiss.githubminer.parsedmodel.ParsedCommit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommitServiceTest {

    @Autowired
    CommitService commitService;

    @Test
    void testGetAllCommits() {
        String owner = "dlozaco";
        String repo = "tetrio-comparator";
        List<ParsedCommit> commits = commitService.getAllCommits(owner, repo);
        assertNotNull(commits, "The project is null");
        assertFalse(commits.isEmpty(), "The project is empty");
        System.out.println(commits);
    }
}