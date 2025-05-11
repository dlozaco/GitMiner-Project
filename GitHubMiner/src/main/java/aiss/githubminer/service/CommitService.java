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

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

    public List<ParsedCommit> getAllCommits(String owner, String repo, Integer sinceCommits, Integer maxPages) {
        String url = baseUri + owner + "/" + repo + "/commits";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        Integer currentPage = 0;
        List<Commit> commits = new ArrayList<>();
        try {
            while (currentPage < maxPages) {
                HttpEntity<Commit[]> request = new HttpEntity<>(null, headers);
                System.out.println("Requesting: " + url);
                ResponseEntity<Commit[]> response = restTemplate.exchange(url, HttpMethod.GET, request, Commit[].class);
                if (response.getBody() != null) {
                    commits.addAll(Arrays.asList(response.getBody()));
                }
                url = getNextPageUrl(response.getHeaders());
                currentPage++;
            }
        } catch (Exception e) {
            System.out.println("Error fetching commits: " + e.getMessage());
        }
        List<ParsedCommit> parsedCommits = parseCommits(commits, sinceCommits);

        return parsedCommits;
    }

    public List<ParsedCommit> parseCommits(List<Commit> commits, Integer sinceCommits) {
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
            //parse date
            String cleanedDate = author_date.replace("Z", "");
            LocalDateTime localDateTime = LocalDateTime.parse(cleanedDate);
            long diference = ChronoUnit.DAYS.between(localDateTime, LocalDateTime.now());
            System.out.println(diference);
            System.out.println("Since commits: " + sinceCommits);
            if(diference < sinceCommits){
                res.add(parsedCommit);
            }
        }
        return res;
    }
    public static String getNextPageUrl(HttpHeaders headers) {
        String result = null;

        // If there is no link header, return null
        List<String> linkHeader = headers.get("Link");
        if (linkHeader == null)
            return null;

        // If the header contains no links, return null
        String links = linkHeader.getFirst();
        if (links == null || links.isEmpty())
            return null;

        // Return the next page URL or null if none.
        for (String token : links.split(", ")) {
            if (token.endsWith("rel=\"next\"")) {
                // Found the next page. This should look something like
                // <https://api.github.com/repos?page=3&per_page=100>; rel="next"
                int idx = token.indexOf('>');
                result = token.substring(1, idx);
                break;
            }
        }

        return result;
    }
}
