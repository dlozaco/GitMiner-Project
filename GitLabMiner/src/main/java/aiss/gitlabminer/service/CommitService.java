package aiss.gitlabminer.service;

import aiss.gitlabminer.model.Commit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CommitService {

    @Autowired
    RestTemplate restTemplate;

    public List<Commit> getAllCommits(String projectId) {
        List<Commit> allCommits = new ArrayList<>();

        boolean hasNextPage = true;
        Integer i = 1;
        while (hasNextPage) {
//            Gets commits of a page
            Commit[] commits = restTemplate.exchange(
                    "https://gitlab.com/api/v4/projects/{projectId}/repository/commits?page={page}&per_page=100",
                    HttpMethod.GET,
                    new HttpEntity<>(new HttpHeaders()),
                    Commit[].class, projectId, i).getBody();

            allCommits.addAll(Arrays.asList(commits));

//            Gets the headers of the response to check if there is a next page
            HttpHeaders responseHeaders = restTemplate.exchange(
                    "https://gitlab.com/api/v4/projects/{projectId}/repository/commits?page={page}&per_page=1",
                    HttpMethod.GET,
                    new HttpEntity<>(new HttpHeaders()),
                    Commit[].class, projectId, i).getHeaders();

//            If there is a next page, increment the page number and continue retrieving commits
            if (responseHeaders.get("x-next-page").getFirst() != "") {
                i++;
            } else {
                hasNextPage = false;
            }
        }
        return allCommits;
    }
}
