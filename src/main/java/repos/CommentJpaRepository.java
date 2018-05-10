package repos;

import org.springframework.data.jpa.repository.JpaRepository;

import models.Comment;

public interface CommentJpaRepository extends JpaRepository<Comment, Integer>{

}
