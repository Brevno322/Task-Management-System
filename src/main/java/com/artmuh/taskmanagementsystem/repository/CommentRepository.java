package com.artmuh.taskmanagementsystem.repository;

import com.artmuh.taskmanagementsystem.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c where c.task.id=:id")
    List<Comment> findByTaskId(@Param("id") Long id);

   Comment save(Comment comment);

}
