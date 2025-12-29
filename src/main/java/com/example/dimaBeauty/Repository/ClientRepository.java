package com.example.dimaBeauty.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.dimaBeauty.Entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
