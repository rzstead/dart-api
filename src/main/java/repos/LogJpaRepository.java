package repos;

import org.springframework.data.jpa.repository.JpaRepository;

import models.Log;

public interface LogJpaRepository extends JpaRepository<Log, Integer> {

}
