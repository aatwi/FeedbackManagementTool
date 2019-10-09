package dev.aatwi.fmtservices.repository;

import dev.aatwi.fmtservices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String>
{

}
