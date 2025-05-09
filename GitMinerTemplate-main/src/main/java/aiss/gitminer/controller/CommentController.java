package aiss.gitminer.controller;


import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Issue;
import aiss.gitminer.repository.CommentRepository;
import aiss.gitminer.repository.IssueRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name="Comment Controller", description = "Comment management API")
@RestController
@RequestMapping("/gitminer")
public class   CommentController {

    @Autowired
    CommentRepository repository;
    @Autowired
    IssueRepository issueRepository;

    //GET
    @Operation(
            summary="Retrieve all comments",
            tags={"comments","get all"}

    )
    @GetMapping("/issues/{issueId}/songs")
    public List<Comment> findAllByIssueId(@PathVariable (value="id") String issueId) {
            Optional<Issue> issue = issueRepository.findById(issueId);

            List<Comment> issueComments= new ArrayList<>(issue.get().getComments());
            return issueComments;
    }

    //GET
    @Operation(
            summary = "Retrieve one comment by id",
            tags={"comments","find by id"}
    )
    @GetMapping("/comments/{commentId}")
    public Comment findCommentById(@PathVariable (value="id") String commentId) {

        Optional<Comment> iss= repository.findById(commentId);
        return iss.get();

    }

}
