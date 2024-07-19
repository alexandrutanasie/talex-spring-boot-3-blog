package talex.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import talex.blog.entities.SiteUser;

import jakarta.transaction.Transactional;

public interface SiteUserRepository extends JpaRepository<SiteUser, Long> {
    SiteUser findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE SiteUser u SET u.password = :encryptedPassword WHERE u.id = :id")
    void updateUserPassword(@Param("id") Long id, @Param("encryptedPassword") String encryptedPassword);
}