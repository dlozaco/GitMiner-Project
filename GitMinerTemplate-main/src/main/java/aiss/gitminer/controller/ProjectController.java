package aiss.gitminer.controller;

import aiss.gitminer.model.Project;
import aiss.gitminer.repository.ProjectRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Tag(name="Project Controller", description = "Project management API")
@RestController
@RequestMapping("/gitminer")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    //GET
    @Operation(
            summary = "Retrieve all projects",
            tags = { "projects", "get all" }
    )
    @GetMapping
    public List<Project> findAllProjects(){ return  projectRepository.findAll(); }

    //POST
    @Operation(
            summary = "Create a blank GitMiner project",
            tags = { "project", "post" }
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Project createProject(@Valid @RequestBody Project project){
        Project newProject = projectRepository.save(
            new Project()
        );
        newProject.setName(project.getName());
        newProject.setWebUrl(project.getWebUrl());
        return project;
    }

    //PUT
    @Operation(
            summary = "Update a GitMiner project",
            tags = { "project", "put" }
    )
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProject(@PathVariable(value = "id") String id, @Valid @RequestBody Project body){
        Optional<Project> projectData = projectRepository.findById(id);

        Project project = projectData.get();
        project.setName(body.getName());
        project.setWebUrl(body.getWebUrl());
        project.setCommits(body.getCommits());
        project.setIssues(body.getIssues());

        projectRepository.save(project);
    }


}
