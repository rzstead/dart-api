package repos;

import org.springframework.data.jpa.repository.JpaRepository;

import models.Challenge;

public interface ChallengeJpaRepository extends JpaRepository<Challenge, Integer> {

}
