package aiss.bitbucketminer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CommitService {

    @Autowired
    RestTemplate restTemplate;

    // Endpoint base para la API de Bitbucket
    final String baseUri = "https://api.bitbucket.org/2.0/repositories";

    public List<Commit> sinceCommits(String workspace, String repoSlug, Integer days, Integer pages, String token) {
        LocalDate date = LocalDate.now().minusDays(days);
        String uri = baseUri + "/" + workspace + "/" + repoSlug + "/commits";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<Commit[]> request = new HttpEntity<>(null, headers);
        ResponseEntity<Commit[]> response = restTemplate.exchange(uri, HttpMethod.GET, request, Commit[].class);

        List<Commit> commits = new ArrayList<>();
        int page = 1;

        while (page <= pages && uri != null) {
            System.out.println(uri);
            List<Commit> commitPage = Arrays.stream(response.getBody()).toList();
            response = restTemplate.exchange(uri, HttpMethod.GET, request, Commit[].class);
            mapCommitValues(commitPage);
            commits.addAll(commitPage);
            uri = RESTUtil.getNextPageUrl(response.getHeaders());
            page++;
        }

        return commits;
    }

    public void mapCommitValues(List<Commit> commits) {
        for (Commit commit : commits) {
            Author author = commit.getAuthor();
            Committer committer = commit.getCommitter();
            String id = commit.getHash();
            String message = commit.getMessage();
            String authorName = author.getUser().getDisplayName();
            String authorEmail = author.getUser().getEmailAddress();
            String authoredDate = author.getDate();
            String committerName = committer.getUser().getDisplayName();
            String committerEmail = committer.getUser().getEmailAddress();
            String committedDate = committer.getDate();
            String webUrl = commit.getLinks().get("html").getHref();

            commit.setId(id);
            commit.setTitle(message.length() < 20 ? message : message.substring(0, 20));
            commit.setMessage(message);
            commit.setAuthorName(authorName);
            commit.setAuthorEmail(authorEmail);
            commit.setAuthoredDate(authoredDate);
            commit.setCommitterName(committerName);
            commit.setCommitterEmail(committerEmail);
            commit.setCommittedDate(committedDate);
            commit.setWebUrl(webUrl);
        }
    }


}
