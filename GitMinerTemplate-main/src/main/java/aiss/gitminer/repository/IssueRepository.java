package aiss.gitminer.repository;

import aiss.gitminer.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, String> {
    List<Issue> findByAuthor_Id(String author_id, Pageable pageable);
    List<Issue> findByState(String state, Pageable pageable);
    List<Issue> findByAuthor_IdAndState(String author_id, String state, Pageable pageable);
}
