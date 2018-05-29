package repos;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import models.Challenge;
import models.Project;

public interface ProjectJpaRepository extends JpaRepository<Project, Integer> {
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<Project> findById(int id);
}
