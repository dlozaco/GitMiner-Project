package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.comment.Comment;
import aiss.bitbucketminer.model.comment.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    RestTemplate restTemplate;

    public List<Comment> getComments(String url) {
        Comment comments = restTemplate.getForObject(url, Comment.class);
        List<Content> content = comments.getContent();


    }


}
