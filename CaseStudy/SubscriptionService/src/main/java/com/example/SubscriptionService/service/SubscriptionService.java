package com.example.SubscriptionService.service;



import java.util.List;

import com.example.SubscriptionService.entity.Subscription;



public interface SubscriptionService {

	//Subscription getSubscription(String subscriberName);
	List<Subscription> getAll();
	Subscription saveUser(Subscription subscription,String bookId);
	Subscription getUserWithFeign(String bookId);
	List<Subscription> getSubscription(String subscriberName);
}
