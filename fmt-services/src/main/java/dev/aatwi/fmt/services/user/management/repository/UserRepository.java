package dev.aatwi.fmt.services.user.management.repository;

import dev.aatwi.fmt.services.user.management.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "SELECT u FROM USER u WHERE UPPER(u.email)=UPPER(:email) and u.password=:password")
    User findUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
