package aiss.gitminer.controller;

import aiss.gitminer.exception.ResourceNotFoundException;
import aiss.gitminer.model.Commit;
import aiss.gitminer.model.Project;
import aiss.gitminer.repository.CommitRepository;
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

import java.util.List;

@Tag(name="Commit Controller", description = "Commit management API")
@RestController
@RequestMapping("/gitminer/commits")
public class CommitController {

    @Autowired
    CommitRepository commitRepository;

    // GET ALL
    @Operation(
            summary = "Retrieve all commits",
            tags = { "commits", "get all" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Commits found", content = { @Content(schema = @Schema(implementation = Commit.class),
                    mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Commits not found", content = @Content(schema = @Schema()))
    })
    @GetMapping
    public List<Commit> findAllCommits(@Parameter(description = "number of pages to show") @RequestParam(defaultValue = "0") int page,
                                       @Parameter(description = "size of pages") @RequestParam(defaultValue = "10") int size,
                                       @Parameter(description = "property to filter") @RequestParam(required = false) String author_name,
                                       @Parameter(description = "order") @RequestParam(required = false) String order){
        Page<Commit> pageCommits;
        Pageable paging;

        if(order!=null && order.equals("desc")){
            paging = PageRequest.of(page, size, Sort.by("id").descending());
        } else {
            paging = PageRequest.of(page, size, Sort.by("id").ascending());
        }

        if(author_name!=null){
            pageCommits = commitRepository.findByAuthorName(author_name, paging);
        } else {
            pageCommits = commitRepository.findAll(paging);
        }

        return pageCommits.getContent();

    }

    //GET BY ID
    @Operation(
            summary = "Retrieve commit by id",
            tags = { "commits", "get by id" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Commit found", content = { @Content(schema = @Schema(implementation = Project.class),
                    mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Commit not found", content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public Commit findOne(@Parameter(description = "id of the commit to search") @PathVariable(value = "id") String id) throws ResourceNotFoundException {
        if(!commitRepository.existsById(id)) {
            throw new ResourceNotFoundException();
        }
        return commitRepository.findById(id).get();
    }
}
