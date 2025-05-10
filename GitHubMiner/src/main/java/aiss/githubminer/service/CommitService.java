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
import java.time.format.DateTimeFormatter;
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


        Integer currentPage = 0;
        List<Commit> commits = new ArrayList<>();
        while(currentPage < maxPages){
            String newUrl = url+"?page=" + currentPage;

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<Commit[]> request = new HttpEntity<>(null, headers);
            System.out.println("Requesting: " + newUrl);
            ResponseEntity<Commit[]> response = restTemplate.exchange(newUrl, HttpMethod.GET, request, Commit[].class);

            commits.addAll(Arrays.asList(response.getBody()));
            if(commits.isEmpty()) break;

            newUrl= url;
            currentPage++;
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
}
