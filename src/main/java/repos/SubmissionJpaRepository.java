package repos;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import models.Challenge;
import models.Submission;

public interface SubmissionJpaRepository extends JpaRepository<Submission, Integer> {
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<Submission> findById(int id);
}
