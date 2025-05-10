package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.Project;
import aiss.bitbucketminer.model.Repository;
import aiss.bitbucketminer.model.Workspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    RestTemplate restTemplate;

    public List<Project> getProjects(String workspace, String repository) {
        Project[] p = null;
        String uri = "https://api.bitbucket.org/2.0/repositories/" + workspace + "/" + repository;
        p = restTemplate.getForObject(uri, Project[].class);
        return Arrays.stream(p).toList();
    }


}
