package click.pranjalonline.blogs.repository;

import click.pranjalonline.blogs.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
     Role findByName(String name);
}
