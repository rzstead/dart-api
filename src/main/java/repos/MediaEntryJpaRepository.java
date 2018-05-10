package repos;

import org.springframework.data.jpa.repository.JpaRepository;

import models.MediaEntry;

public interface MediaEntryJpaRepository extends JpaRepository<MediaEntry, Integer> {

}
