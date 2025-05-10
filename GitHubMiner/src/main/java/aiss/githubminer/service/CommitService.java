package aiss.githubminer.service;

import aiss.githubminer.model.commit.Author;
import aiss.githubminer.model.commit.Commit;
import aiss.githubminer.model.commit.Commit__1;
import aiss.githubminer.parsedmodel.ParsedCommit;
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
public class CommitService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${github.api.token}")
    private String token;

    String baseUri = "https://api.github.com/repos/";

    public List<ParsedCommit> getAllCommits(String owner, String repo) {
        String url = baseUri + owner + "/" + repo + "/commits";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<Commit[]> request = new HttpEntity<>(null, headers);
        ResponseEntity<Commit[]> response = restTemplate.exchange(url, HttpMethod.GET, request, Commit[].class);

        List<Commit> commits = Arrays.asList(response.getBody());
        List<ParsedCommit> parsedCommits = parseCommits(commits);

        return parsedCommits;
    }

    public List<ParsedCommit> parseCommits(List<Commit> commits){
        List<ParsedCommit> res = new ArrayList<>();
        for(Commit commit:commits){
            Commit__1 body = commit.getCommit();
            Author author = body.getAuthor();
            String sha = commit.getSha();
            String title =  body.getMessage().split(" ")[0];
            String message = body.getMessage();
            String author_name = author.getName();
            String author_email = author.getEmail();
            String author_date = author.getDate();
            String url = body.getUrl();
            ParsedCommit parsedCommit = new ParsedCommit(
                    sha, title, message, author_name, author_email, author_date, url
            );
            res.add(parsedCommit);
        }
        return res;
    }
}
