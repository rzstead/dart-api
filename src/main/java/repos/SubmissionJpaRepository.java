package repos;

import org.springframework.data.jpa.repository.JpaRepository;

import models.Submission;

public interface SubmissionJpaRepository extends JpaRepository<Submission, Integer> {

}
