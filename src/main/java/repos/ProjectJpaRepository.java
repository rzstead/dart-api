package repos;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import models.Project;

public interface ProjectJpaRepository extends JpaRepository<Project, Integer> {
}
