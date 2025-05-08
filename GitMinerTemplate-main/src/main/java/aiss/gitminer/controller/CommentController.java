package aiss.gitminer.controller;


import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Issue;
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
@RequestMapping("/api/comments")
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
    public List<Comment> findAllByIssueId(@PathVariable (value="issueId") long issueId) {
            Optional<Issue> issue = issueRepository.findById();

            List<Comment> IssueComments= new ArrayList<>(issue.get().getComments());
            return IssueComments;
    }

    //GET
    @Operation(
            summary = "Retrieve one comment by id",
            tags={"comments","find by id"}
    )
    @GetMapping("/comments/{commentId}")
    public Comment findById(@PathVariable (value="id") long issueId) {



    }

}
