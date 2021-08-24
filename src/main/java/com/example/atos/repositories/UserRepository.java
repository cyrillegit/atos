package com.example.atos.repositories;

import com.example.atos.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * find user knowing its username
     * @param username of the user
     * @return corresponding user
     */
    Optional<User> findByUsername(String username);

    /**
     * find user knowing its id
     * @param id of the user
     * @return corresponding user
     */
    Optional<User> findById(Integer id);
}
