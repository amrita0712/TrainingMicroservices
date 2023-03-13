package com.example.SubscriptionService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SubscriptionService.entity.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, String> {
	//Optional<Subscription> findByName(String subscriberName);
	
	List<Subscription> findByBookId(String bookId);
	List<Subscription> findAll();
	List<Subscription> findBySubscriberName(String subscriberName);
}
