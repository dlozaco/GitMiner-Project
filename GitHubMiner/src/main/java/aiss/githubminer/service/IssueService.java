package aiss.githubminer.service;

import aiss.githubminer.model.commit.Commit;
import aiss.githubminer.model.issue.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IssueService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${github.api.token}")
    private String token;

    String baseUri = "https://api.github.com/repos/";

    public Issue[] getAllIssues(String owner, String repo){
        String url = baseUri + owner + "/" + repo + "/issues";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + token);

        HttpEntity<Issue[]> request = new HttpEntity<>(null, headers);
        ResponseEntity<Issue[]> response = restTemplate.exchange(url, HttpMethod.GET, request, Issue[].class);

        return response.getBody();
    }
}
