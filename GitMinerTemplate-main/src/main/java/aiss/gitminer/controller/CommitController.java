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
    @GetMapping
    public List<Commit> findAllCommits(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(required = false) String author_name,
                                       @RequestParam(required = false) String order){
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
