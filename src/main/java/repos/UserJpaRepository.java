package repos;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import models.Challenge;
import models.User;

public interface UserJpaRepository extends JpaRepository<User, String> {
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<User> findById(String username);
}
