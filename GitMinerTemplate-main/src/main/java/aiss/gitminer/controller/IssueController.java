package aiss.gitminer.controller;


import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Issue;
import aiss.gitminer.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gitminer/issues")
public class IssueController {

    @Autowired
    IssueRepository issueRepository;

    @GetMapping
    public List<Issue> getAllIssues(@RequestParam(required = false) String author_id,
                                    @RequestParam(required = false) String state) {

        if (author_id != null && state != null) {
            List<Issue> res = issueRepository.findByAuthor_Id(author_id);
            res.removeIf(issue -> !issue.getState().equals(state));
            return res;
        }
        if (author_id != null) {
            return issueRepository.findByAuthor_Id(author_id);
        }
        if (state != null) {
            return issueRepository.findByState(state);
            }
        return issueRepository.findAll();
    }

    @GetMapping("/{id}")
    public Issue getIssueById(@PathVariable Long id) {
        Optional<Issue> issue = issueRepository.findById(id);
        return issue.orElse(null);
    }

    @GetMapping("/{id}/comments")
    public List<Comment>  getAllCommentsByIssueId(@PathVariable Long id) {
        Issue issue = getIssueById(id);
        return issue.getComments();
    }

}
