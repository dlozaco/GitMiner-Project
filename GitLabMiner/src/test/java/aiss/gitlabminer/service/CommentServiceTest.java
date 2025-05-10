package aiss.gitlabminer.service;

import aiss.gitlabminer.model.Comment;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Test for getAllComments method")
    void getAllComments() {
        List<Comment> comments = commentService.getAllComments("69720129", 1);
        assertEquals(3, comments.size(), "Project comments size is incorrect");
        comments.forEach(comment -> {
            assertNotNull(comment.getId(), "Comment id is null");
            assertNotNull(comment.getBody(), "Comment body is null");
            assertNotNull(comment.getCreatedAt(), "Comment created at is null");
            assertNotNull(comment.getAuthor(), "Comment author is null");
            assertNotNull(comment.getAuthor().getId(), "Comment author id is null");
            assertNotNull(comment.getAuthor().getUsername(), "Comment author username is null");
        });
    }
}