package aiss.gitminer.controller;


import aiss.gitminer.exception.ResourceNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Issue;
import aiss.gitminer.repository.CommentRepository;
import aiss.gitminer.repository.IssueRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name="Comment Controller", description = "Comment management API")
@RestController
@RequestMapping("/gitminer/comments")
public class   CommentController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    IssueRepository issueRepository;

    //GET
    @Operation(
            summary="Retrieve all comments",
            tags={"comments","get all"}

    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Comments found", content = { @Content(schema = @Schema(implementation = Comment.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Comments not found", content = @Content(schema = @Schema()))
    })
    @GetMapping
    public List<Comment> findAllComments(@Parameter(description = "number of pages to show") @RequestParam(defaultValue = "0") int page,
                                         @Parameter(description = "size of pages") @RequestParam(defaultValue = "10") int size,
                                         @Parameter(description = "property to filter") @RequestParam(required = false) String authorId,
                                         @Parameter(description = "order") @RequestParam(required = false) String order) {
        Page<Comment> pageComments;
        Pageable paging = PageRequest.of(page,size);
        if (order!=null){
            if(order.startsWith("-")){
                paging= PageRequest.of(page,size,Sort.by(order.substring(1)).descending());
            } else{
                paging= PageRequest.of(page,size,Sort.by(order).ascending());
            }
        }

        if (authorId!=null){
            pageComments= commentRepository.findByAuthorId(authorId,paging);
        } else{
            pageComments= commentRepository.findAll(paging);
        }
        return pageComments.getContent();
    }

    //GET
    @Operation(
            summary = "Retrieve one comment by id",
            tags={"comments","get by id"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Comment found", content = { @Content(schema = @Schema(implementation = Comment.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Comment not found", content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public Comment findCommentById(@Parameter(description = "id of the issue to search") @PathVariable (value="id") String commentId) throws ResourceNotFoundException {

        Optional<Comment> iss= commentRepository.findById(commentId);
        if (iss.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return iss.get();

    }

    //POST
    @Operation(
            summary = "Create a new comment",
            tags={"comments","post"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Comment created", content = { @Content(schema = @Schema(implementation = Comment.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema()))
    })
    @PostMapping("/{issueId}")
    public Comment createComment(@Parameter(description = "body of the comment to be created") @Valid @RequestBody Comment comment,
                                    @Parameter(description = "id of the issue to which the comment belongs") @PathVariable(value = "issueId") String issueId)
    throws ResourceNotFoundException {
        Optional<Issue> issueData = issueRepository.findById(issueId);
        if(issueData.isEmpty()){
            throw new ResourceNotFoundException();
        }

        Issue issue = issueData.get();
        List<Comment> comments = issue.getComments();
        comments.add(comment);
        issue.setComments(comments);

        issueRepository.save(issue);
        return comment;
    }

    //PUT
    @Operation(
            summary = "Update a comment",
            tags={"comments","put"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Comment updated", content = { @Content(schema = @Schema(implementation = Comment.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Comment not found", content = @Content(schema = @Schema()))
    })
    @PutMapping("/{id}")
    public void updateComment(@Parameter(description = "id of the comment to be updated") @PathVariable(value = "id") String id,
                              @Parameter(description = "body of the comment to be updated") @Valid @RequestBody Comment body)
            throws ResourceNotFoundException {
        Optional<Comment> commentData = commentRepository.findById(id);
        if(commentData.isEmpty()){
            throw new ResourceNotFoundException();
        }

        Comment comment = commentData.get();
        comment.setId(body.getId());
        comment.setBody(body.getBody());
        comment.setAuthor(body.getAuthor());
        comment.setCreatedAt(body.getCreatedAt());
        comment.setUpdatedAt(body.getUpdatedAt());

        commentRepository.save(comment);
    }

    //DELETE
    @Operation(
            summary = "Delete a comment",
            tags={"comments","delete"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Comment deleted", content = { @Content(schema = @Schema(implementation = Comment.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Comment not found", content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public void deleteComment(@Parameter(description = "id of the comment to be deleted") @PathVariable(value = "id") String id)
            throws ResourceNotFoundException {
        if(commentRepository.existsById(id)){
            commentRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
