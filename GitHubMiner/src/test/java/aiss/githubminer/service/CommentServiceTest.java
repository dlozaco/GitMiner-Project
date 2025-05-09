package aiss.githubminer.service;

import aiss.githubminer.model.issue.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Test
    void getAllComments() {
        String url = "https://api.github.com/repos/dlozaco/tetrio-comparator/issues/1/comments";
        List<Comment> comments = commentService.getAllComments(url);
        assertNotNull(comments);
        System.out.println(comments);
    }
}