package com.nimuairy.socialnetwork;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactsRepository extends JpaRepository<Contact, Long> {

	List<Contact> findByFirstUserIdOrSecondUserId(Long firstUserId, Long secondUserId);
}
