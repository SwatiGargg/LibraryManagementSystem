package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import com.example.demo.dto.Users;
import com.example.repository.PsqlRepository;

@Repository
public interface UserRepository extends PsqlRepository<Users, Long>{
	Users findByUsername(String username);
    boolean existsByUsername(String username);
}
