package repos;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import models.Challenge;
import models.MediaEntry;

public interface MediaEntryJpaRepository extends JpaRepository<MediaEntry, Integer> {
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<MediaEntry> findById(int id);
}
