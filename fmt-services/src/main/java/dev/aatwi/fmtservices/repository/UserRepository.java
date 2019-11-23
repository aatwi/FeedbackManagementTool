package dev.aatwi.fmtservices.repository;

import dev.aatwi.fmtservices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>
{
    User findUserByEmailAndPassword(String email, String password);
}
