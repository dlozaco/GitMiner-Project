package aiss.gitminer.controller;


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
            tags = { "issues", "getAll", "filter"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Issue.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema()))
    })

    @GetMapping
    public List<Issue> getAllIssues(@Parameter(description = "Obtaining all the ISSUES, and you can filter for the Author's ID or the state of the ISSUE")
                                        @RequestParam(required = false) String author_id,
                                        @RequestParam(required = false) String state) {

        if (author_id != null && state != null) {
            return issueRepository.findByAuthor_IdAndState(author_id, state);
        }
        if (author_id != null) {
            return issueRepository.findByAuthor_Id(author_id);
        }
        if (state != null) {
            return issueRepository.findByState(state);
            }
        return issueRepository.findAll();
    }

    @Operation(
            summary = "Retrieve an issue from the id ",
            description = "Get an issue from the id",
            tags = { "issue","get", "byId",}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Issue.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public Issue getIssueById(@PathVariable String id) {
        Optional<Issue> issue = issueRepository.findById(id);
        return issue.orElse(null);
    }

    @Operation(
            summary = "Retrieve a list of comments",
            description = "Get a list of comments from the issue Id",
            tags = { "issues", "getAll", "filter"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Issue.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}/comments")
    public List<Comment>  getAllCommentsByIssueId(@PathVariable String id) {
        Issue issue = getIssueById(id);
        return issue.getComments();
    }

}
