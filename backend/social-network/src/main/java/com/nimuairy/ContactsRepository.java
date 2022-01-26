package com.nimuairy;

import com.nimuairy.auth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ContactsRepository extends JpaRepository<Contact, Long> {

	Set<Contact> findByFirstUserIdOrSecondUserId(Long firstUserId, Long secondUserId);
}
