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
    public List<Project> findAllProjects(){
        List<Project> gitMinerProjects = projectRepository.findAll();
        return gitMinerProjects;
    }

    //POST
    @Operation(
            summary = "Create a blank GitMiner project",
            tags = { "project", "post" }
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Project createProject(@Valid @RequestBody Project project){
        Project newProject = projectRepository.save(
            new Project(project.getName(), project.getWebUrl())
        );
        return project;
    }


}
