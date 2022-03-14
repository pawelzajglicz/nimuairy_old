package com.nimuairy.auth.repository;

import com.nimuairy.auth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String name);

	Boolean existsByUsername(String name);

	Boolean existsByEmail(String email);

	List<User> getByIdIn(List<Long> userIds);
}

