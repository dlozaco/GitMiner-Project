package aiss.gitminer.controller;

import aiss.gitminer.exception.ResourceNotFoundException;
import aiss.gitminer.model.Project;
import aiss.gitminer.repository.ProjectRepository;
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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Tag(name="Project Controller", description = "Project management API")
@RestController
@RequestMapping("/gitminer/projects")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    // GET ALL
    @Operation(
            summary = "Retrieve all projects",
            tags = { "projects", "get all" }
    )
    @GetMapping
    public List<Project> findAllProjects(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(required = false) String name,
                                         @RequestParam(required = false) String order){
        Page<Project> pageProjects;
        Pageable paging;

        if(order!=null && order.equals("desc")){
            paging = PageRequest.of(page, size, Sort.by("id").descending());
        } else {
            paging = PageRequest.of(page, size, Sort.by("id").ascending());
        }

        if(name!=null){
            pageProjects = projectRepository.findByName(name, paging);
        } else {
            pageProjects = projectRepository.findAll(paging);
        }
        return pageProjects.getContent();
    }

    //GET BY ID
    @Operation(
            summary = "Retrieve project by id",
            tags = { "project", "get by id" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Project found", content = { @Content(schema = @Schema(implementation = Project.class),
                    mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Project not found", content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public Project findOne(@Parameter(description = "id of the project to search") @PathVariable(value = "id") String id) throws ResourceNotFoundException{
        if (!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException();
        }
        return projectRepository.findById(id).get();
    }

    //POST
    @Operation(
            summary = "Create a blank GitMiner project",
            tags = { "project", "post" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Project created", content = { @Content(schema = @Schema(implementation = Project.class),
                    mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema()))
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Project createProject(@Parameter(description = "body of the project to be created") @Valid @RequestBody Project project){
        return projectRepository.save(project);
    }

    //PUT
    @Operation(
            summary = "Update a GitMiner project",
            tags = { "project", "put" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Project updated", content = { @Content(schema = @Schema(implementation = Project.class),
                    mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Project not found", content = @Content(schema = @Schema()))
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProject(@Parameter(description = "id of the project to be updated" ) @PathVariable(value = "id") String id,
                              @Parameter(description = "body of the project to be updated") @Valid @RequestBody Project body) throws ResourceNotFoundException{
        Optional<Project> projectData = projectRepository.findById(id);
        if (projectData.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        Project project = projectData.get();
        project.setName(body.getName());
        project.setWebUrl(body.getWebUrl());
        project.setCommits(body.getCommits());
        project.setIssues(body.getIssues());

        projectRepository.save(project);
    }

    //DELETE
    @Operation(
            summary =  "Delete a GitMiner project",
            tags = { "project", "delete" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Project deleted", content = { @Content(schema = @Schema(implementation = Project.class),
                    mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Project not found", content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@Parameter(description = "id of the project to be deleted" ) @PathVariable(value = "id") String id) throws ResourceNotFoundException {
        if(projectRepository.existsById(id)){
            projectRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
