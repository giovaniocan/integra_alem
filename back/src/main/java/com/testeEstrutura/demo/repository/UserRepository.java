package com.testeEstrutura.demo.repository;

import com.testeEstrutura.demo.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
