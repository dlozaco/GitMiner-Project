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

    public List<ParsedCommit> getCommits(String url, Integer nCommits, Integer maxPages) {
        // CommitPage commits = null;
        CommitPage commits = restTemplate.getForObject(url, CommitPage.class);
        return parseCommits(commits, nCommits, maxPages);
    }

    public List<ParsedCommit> parseCommits(CommitPage commits, Integer nCommits, Integer maxPages) {
        List<ParsedCommit> parsedCommits = new ArrayList<>();

        for (Commit commit : commits.getCommits()) {
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

            if (parsedCommits.size() == nCommits) {
                break;
            }
        }
        if (commits.getNext() != null && (Integer.valueOf(commits.getNext().split("&page=")[1]))-1 < maxPages) {
            parsedCommits.addAll(getCommits(commits.getNext(), nCommits, maxPages));
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
