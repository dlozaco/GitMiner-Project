package aiss.gitlabminer.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GitLabServiceTest {

    @Autowired
    GitLabService gitLabService;

    @Test
    @DisplayName( "Test for getProject method")
    void getProject() {
        System.out.println(gitLabService.getProject("gitlab-org", "gitlab"));
    }
}