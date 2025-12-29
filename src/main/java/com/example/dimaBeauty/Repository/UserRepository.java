package com.example.dimaBeauty.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.dimaBeauty.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}