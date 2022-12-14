package exercise.repository;

import exercise.model.Comment;
import liquibase.pro.packaged.C;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    Iterable<Comment> findAllByPostId(Long id);

    Optional<Comment> findFirstByIdAndPostId(Long commentId, Long postId);
}
