package click.pranjalonline.blogs.repository;

import click.pranjalonline.blogs.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllCommentsByPostId(Long PostId);
}
