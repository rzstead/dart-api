package repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import models.User;

public interface UserJpaRepository extends JpaRepository<User, String> {

	
}
