package com.att.attcare.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.att.attcare.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

List<User> findAllByRole(String string);

Optional<User> findById(Long id);

void deleteById(Long id);

}
