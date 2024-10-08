package com.InstaTalk.repositories;

import com.InstaTalk.models.User;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRespository extends JpaRepository<User, Integer> {
    public User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.firstName LIKE %:query% OR u.lastName LIKE %:query%")
    public List<User> searchUser(@Param("query") String query);
}
