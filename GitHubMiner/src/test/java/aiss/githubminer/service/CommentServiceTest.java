package aiss.githubminer.service;

import aiss.githubminer.model.issue.Comment;
import aiss.githubminer.parsedmodel.ParsedComment;
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
    void testGetAllComments() {
        String url = "https://api.github.com/repos/dlozaco/tetrio-comparator/issues/1/comments";
        List<ParsedComment> comments = commentService.getAllComments(url);
        assertNotNull(comments, "Comments are null");
        assertFalse(comments.isEmpty(), "Comments are empty");
        System.out.println(comments);
    }
}