package aiss.gitlabminer.service;

import aiss.gitlabminer.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitLabService {

    @Autowired
    RestTemplate restTemplate;

    /*For the request of a project by its owner and name, the path https://gitlab.com/api/v4/projects/ only takes one
argument which is the full path owner/project, so, for the path to not process each one as a different argument, / must be
replaced by %2F in Postman requests, declared as a single parameter here*/

    public Project getProject(String owner, String name) {
        String param = owner + "/" + name;
        return restTemplate.getForObject("https://gitlab.com/api/v4/projects/{param}", Project.class, param);
    }
}
