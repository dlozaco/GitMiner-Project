package aiss.gitminer.model.repository;

import aiss.gitminer.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByAuthor_Id(String author_id);
    List<Issue> findByState(String state);
}
