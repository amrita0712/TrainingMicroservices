package com.example.SubscriptionService.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SubscriptionService.entity.Subscription;
import com.example.SubscriptionService.service.SubscriptionService;
import com.example.SubscriptionService.service.SubscriptionServiceImpl;

@RestController
@RequestMapping("subscriptions")
public class SubscriptionController {
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	// creating a logger
    Logger logger
        = LoggerFactory.getLogger(SubscriptionServiceImpl.class);
	
    @GetMapping
	public List<Subscription> getAll() {
		logger.info("getAll() method.");
		return subscriptionService.getAll();
	}
    
	@GetMapping("/{subscriberName}")
	public List<Subscription> getUser(@PathVariable("subscriberName") String subscriberName) {
		logger.info("getUser() method.");
		return subscriptionService.getSubscription(subscriberName);
	}
	
	@PostMapping("/subscriptions")
	public ResponseEntity<Subscription> addUser(@RequestBody Subscription user) {
		Subscription savedUser = null;
		String id=user.getBookId();
		HttpStatus status = HttpStatus.CREATED;
		try {
			savedUser = subscriptionService.saveUser(savedUser,id);
		} catch(Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}		
				
		return new ResponseEntity<>(savedUser, status);
	}
	
	@GetMapping("withFeign/{bookId}")
	public Subscription getUserWithFeign(@PathVariable("bookId") String bookId) {
		
		return subscriptionService.getUserWithFeign(bookId);
	}
	
}
