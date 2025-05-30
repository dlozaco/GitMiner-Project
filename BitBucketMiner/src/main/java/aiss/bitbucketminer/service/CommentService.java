package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.comment.Comment;
import aiss.bitbucketminer.model.comment.CommentPage;
import aiss.bitbucketminer.model.comment.User;
import aiss.bitbucketminer.model.parsedModels.ParsedComment;
import aiss.bitbucketminer.model.parsedModels.ParsedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserService userService;

    public List<ParsedComment> getComments(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<CommentPage> response = restTemplate.exchange(url, HttpMethod.GET, request, CommentPage.class);

        List<Comment> comments = response.getBody().getComments();
        return parseComments(comments);


    }

    public List<ParsedComment> parseComments(List<Comment> comments) {
        List<ParsedComment> parsedComments= new ArrayList<ParsedComment>();
        for (Comment comment : comments) {
            String id = comment.getId().toString();
            String body = comment.getContent().getRaw();
            String createdAt = comment.getCreatedOn().toString();
            String updatedAt = comment.getUpdatedOn() != null ? comment.getUpdatedOn().toString() : null;
            ParsedUser author = userService.parseUser(comment.getUser());
            if(body == null){
                body = "No body";
            }
            ParsedComment parsedComment = new ParsedComment(id, body, createdAt, updatedAt, author);
            parsedComments.add(parsedComment);
        }
        return parsedComments;
    }


}
