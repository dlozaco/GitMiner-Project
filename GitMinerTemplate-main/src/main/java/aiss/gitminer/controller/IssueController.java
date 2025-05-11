package aiss.gitminer.controller;


import aiss.gitminer.exception.ResourceNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Issue;
import aiss.gitminer.repository.IssueRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Tag(name="Issue Controller", description = "Issue management API")
@RestController
@RequestMapping("/gitminer/issues")
public class IssueController {

    @Autowired
    IssueRepository issueRepository;

    @Operation(
            summary = "Retrieve a list of issues",
            description = "Get a list of issues and you can filter for the Author's ID or the state of the issue",
            tags = { "issues", "get all", "filter"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Issues found", content = { @Content(schema = @Schema(implementation = Issue.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema()))
    })
    @GetMapping
    public List<Issue> getAllIssues(@Parameter(description = "number of pages to show") @RequestParam(defaultValue = "0") int page,
                                    @Parameter(description = "size of pages") @RequestParam(defaultValue = "10") int size,
                                    @Parameter(description = "first property to filter") @RequestParam(required = false) String author_id,
                                    @Parameter(description = "second property to filter")@RequestParam(required = false) String state,
                                    @Parameter(description = "order")@RequestParam(required=false) String order){

        Pageable paging = PageRequest.of(page,size);
        if (order!=null){
            if(order.startsWith("-")){
                paging= PageRequest.of(page,size, Sort.by(order.substring(1)).descending());
            } else{
                paging= PageRequest.of(page,size,Sort.by(order).ascending());
            }
        }

        if (author_id != null && state != null) {
            return issueRepository.findByAuthor_IdAndState(author_id, state, paging);
        }
        if (author_id != null) {
            return issueRepository.findByAuthor_Id(author_id, paging);
        }
        if (state != null) {
            return issueRepository.findByState(state, paging);
            }
        return issueRepository.findAll(paging).getContent();
    }


    @Operation(
            summary = "Retrieve an issue from the id ",
            description = "Get an issue from the id",
            tags = { "issue", "get by id",}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Issue found", content = { @Content(schema = @Schema(implementation = Issue.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public Issue getIssueById(@Parameter(description = "id of the issue to search") @PathVariable String id) throws ResourceNotFoundException {
        Optional<Issue> issue = issueRepository.findById(id);
        if (issue.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return issue.orElse(null);
    }


    @Operation(
            summary = "Retrieve a list of comments",
            description = "Get a list of comments from the issue Id",
            tags = { "issues", "get all", "filter"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Issue found", content = { @Content(schema = @Schema(implementation = Issue.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}/comments")
    public List<Comment>  getAllCommentsByIssueId(@Parameter(description = "id of the issue to search") @PathVariable String id) throws ResourceNotFoundException {
        Issue issue = getIssueById(id);
        return issue.getComments();
    }
}
