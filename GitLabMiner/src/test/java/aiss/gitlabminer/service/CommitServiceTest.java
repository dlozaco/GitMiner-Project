package aiss.gitlabminer.service;

import aiss.gitlabminer.model.Commit;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName( "Test for getAllCommits method")
    void getAllCommits() {
        List<Commit> commits = commitService.getAllCommits("69720129");
        assertEquals(4, commits.size(), "Project commits size is incorrect");
        commits.forEach(commit -> {
            assertNotNull(commit.getId(), "Commit id is null");
            assertNotNull(commit.getAuthoredDate(), "Commit authored date is null");
            assertNotNull(commit.getAuthorName(), "Commit author name is null");
            assertNotNull(commit.getWebUrl(), "Commit web url is null");
        });
    }
}