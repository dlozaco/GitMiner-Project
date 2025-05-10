package aiss.githubminer.service;

import aiss.githubminer.model.issue.Comment;
import aiss.githubminer.model.issue.User;
import aiss.githubminer.parsedmodel.ParsedComment;
import aiss.githubminer.parsedmodel.ParsedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${github.api.token}")
    private String token;

    public List<ParsedComment> getAllComments(String url){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Comment[]> request = new HttpEntity<>(null, headers);
        ResponseEntity<Comment[]> response = restTemplate.exchange(url, HttpMethod.GET, request, Comment[].class);
        List<Comment> comments = Arrays.asList(response.getBody());
        List<ParsedComment> parsedComments = parseComments(comments);
        return parsedComments;
    }

    private List<ParsedComment> parseComments(List<Comment> comments) {
        List<ParsedComment> res = new ArrayList<>();
        for(Comment comment:comments){
            ParsedComment parsedComment = new ParsedComment(
                    String.valueOf(comment.getId()),
                    comment.getBody(),
                    parseUser(comment.getUser()),
                    comment.getCreatedAt(),
                    comment.getUpdatedAt()
            );
            res.add(parsedComment);
        }
        return res;
    }

    private ParsedUser parseUser(User user) {
        return new ParsedUser(
                user.getId(),
                user.getLogin(),
                user.getLogin().replaceAll("[^a-zA-Z0-9]", ""),
                user.getAvatarUrl(),
                user.getHtmlUrl()
        );
    }
}
