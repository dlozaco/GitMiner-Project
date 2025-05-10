package aiss.gitminer.controller;


import aiss.gitminer.exception.ResourceNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Issue;
import aiss.gitminer.repository.CommentRepository;
import aiss.gitminer.repository.IssueRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name="Comment Controller", description = "Comment management API")
@RestController
@RequestMapping("/gitminer/comments")
public class   CommentController {

    @Autowired
    CommentRepository repository;

    //GET
    @Operation(
            summary="Retrieve all comments",
            tags={"comments","get all"}

    )
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
            pageComments= repository.findByAuthorId(authorId,paging);
        } else{
            pageComments= repository.findAll(paging);
        }
        return pageComments.getContent();
    }

    //GET
    @Operation(
            summary = "Retrieve one comment by id",
            tags={"comments","get by id"}
    )
    @GetMapping("/{id}")
    public Comment findCommentById(@Parameter(description = "id of the issue to search") @PathVariable (value="id") String commentId) throws ResourceNotFoundException {

        Optional<Comment> iss= repository.findById(commentId);
        if (iss.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return iss.get();

    }

}
