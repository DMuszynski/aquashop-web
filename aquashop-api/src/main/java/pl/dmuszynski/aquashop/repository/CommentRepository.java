package pl.dmuszynski.aquashop.repository;

import org.springframework.data.repository.CrudRepository;
import pl.dmuszynski.aquashop.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}
