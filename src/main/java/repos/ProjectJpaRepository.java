package repos;

import org.springframework.data.jpa.repository.JpaRepository;

import models.Project;

public interface ProjectJpaRepository extends JpaRepository<Project, Integer> {

}
