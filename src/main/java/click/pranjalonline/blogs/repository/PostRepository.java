package click.pranjalonline.blogs.repository;

import click.pranjalonline.blogs.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post,Long> {
}
