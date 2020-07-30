package pl.dmuszynski.aquashop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.dmuszynski.aquashop.model.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

}
