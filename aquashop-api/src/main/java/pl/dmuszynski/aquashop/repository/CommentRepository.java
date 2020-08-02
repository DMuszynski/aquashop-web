package pl.dmuszynski.aquashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dmuszynski.aquashop.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Modifying @Query(value = "UPDATE Comment c SET c.description = :description WHERE c.id = :id")
    void updateDescriptionById(@Param("description") String description, @Param("id") Long id);

    @Modifying @Query(value = "UPDATE Comment c SET c.mark = :mark WHERE c.id = :id")
    void updateMarkById(@Param("mark") int mark, @Param("id") Long id);
}
