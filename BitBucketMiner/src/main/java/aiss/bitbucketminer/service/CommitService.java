package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.commit.Commit;
import aiss.bitbucketminer.model.parsedModels.ParsedCommit;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CommitService {

    @Autowired
    RestTemplate restTemplate;

    public List<ParsedCommit> getCommits(String owner, String repo) {
        String uri = "https://api.bitbucket.org/2.0/repositories/" + owner + "/" + repo + "/commits";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Commit[]> request = new HttpEntity<>(null, headers);

        ResponseEntity<Commit[]> response = restTemplate.exchange(uri, HttpMethod.GET, request, Commit[].class);

        List<Commit> commits = Arrays.asList(response.getBody());
        return parseCommits(commits);
    }

    public List<ParsedCommit> parseCommits(List<Commit> commits) {
        List<ParsedCommit> parsedCommits = new ArrayList<>();
        for (Commit commit : commits) {
            String hash = commit.getHash();
            String message = commit.getMessage();
            String title = message.split("\n")[0];
            String authorName = commit.getAuthor().getUser().getDisplayName();
            String authorEmail = commit.getAuthor().getRaw(); //TO STRING ??????
            String authoredDate = commit.getDate(); //TO STRING ??????
            String webUrl = commit.getLinks().getHtml().getHref(); //getHtml o getSelf???

            ParsedCommit parsedCommit = new ParsedCommit(hash, title, message, authorName, authorEmail, authoredDate, webUrl);
            parsedCommits.add(parsedCommit);
        }
        return parsedCommits;
    }
}
