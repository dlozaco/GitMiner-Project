package aiss.gitlabminer.service;

import aiss.gitlabminer.model.Comment;
import aiss.gitlabminer.model.Commit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CommentService {

    @Autowired
    RestTemplate restTemplate;

//    Token is needed for retrieving comments
    @Value("${gitlab.token}")
    private String token;

    public List<Comment> getAllComments(String projectId, Integer issueIid) {
        List<Comment> allComments = new ArrayList<>();

        boolean hasNextPage = true;
        Integer i = 1;
        try {
            while (hasNextPage) {

//                 Gets comments of a page
                Comment[] comments = restTemplate.exchange(
                        "https://gitlab.com/api/v4/projects/{projectId}/issues/{issueIid}/notes?page={page}&per_page=100&private_token={token}",
                        HttpMethod.GET,
                        new HttpEntity<>(new HttpHeaders()),
                        Comment[].class, projectId, issueIid, i, token).getBody();

                allComments.addAll(Arrays.asList(comments));

//                 Gets the headers of the response to check if there is a next page
                HttpHeaders responseHeaders = restTemplate.exchange(
                        "https://gitlab.com/api/v4/projects/{projectId}/issues/{issueIid}/notes?page={page}&per_page=100&private_token={token}",
                        HttpMethod.GET,
                        new HttpEntity<>(new HttpHeaders()),
                        Commit[].class, projectId, issueIid, i, token).getHeaders();

//                 If there is a next page, increment the page number and continue retrieving comments
                if (responseHeaders.get("x-next-page").getFirst() != "") {
                    i++;
                } else {
                    hasNextPage = false;
                }
            }
        } catch (HttpClientErrorException.Unauthorized e) {
            System.out.println("Error getting comments for issue " + issueIid + ": " + e.getMessage());
        }

        return allComments;
    }
}
