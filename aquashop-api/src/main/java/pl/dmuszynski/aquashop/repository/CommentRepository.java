package pl.dmuszynski.aquashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dmuszynski.aquashop.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
