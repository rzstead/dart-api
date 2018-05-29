package repos;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import models.Challenge;

public interface ChallengeJpaRepository extends JpaRepository<Challenge, Integer> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<Challenge> findById(int id);
}
