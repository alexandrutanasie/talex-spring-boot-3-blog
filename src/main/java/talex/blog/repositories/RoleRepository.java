package talex.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import talex.blog.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}