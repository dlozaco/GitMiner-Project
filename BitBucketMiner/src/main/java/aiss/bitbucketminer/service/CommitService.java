package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.commit.Commit;
import aiss.bitbucketminer.model.commit.CommitPage;
import aiss.bitbucketminer.model.parsedModels.ParsedCommit;
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
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        ResponseEntity<CommitPage> response = restTemplate.exchange(uri, HttpMethod.GET, request, CommitPage.class);

        List<Commit> commits = response.getBody().getCommits();
        return parseCommits(commits);
    }

    public List<ParsedCommit> parseCommits(List<Commit> commits) {
        List<ParsedCommit> parsedCommits = new ArrayList<>();

        for (Commit commit : commits) {
            String hash = commit.getHash();
            String message = commit.getMessage();
            String title = message.split("\n")[0];

            String authorName = commit.getAuthor().getUser().getDisplayName();
            String authorRaw = commit.getAuthor().getRaw();
            String authorEmail = extractEmail(authorRaw);

            String authoredDate = commit.getDate();
            String webUrl = commit.getLinks().getHtml().getHref();

            ParsedCommit parsedCommit = new ParsedCommit(hash, title, message, authorName, authorEmail, authoredDate, webUrl);
            parsedCommits.add(parsedCommit);
        }
        return parsedCommits;
    }

    private String extractEmail(String raw) {
        if (raw.contains("<") && raw.contains(">")) {
            return raw.substring(raw.indexOf("<") + 1, raw.indexOf(">"));
        } else {
            return "No email available";
        }
    }
}
