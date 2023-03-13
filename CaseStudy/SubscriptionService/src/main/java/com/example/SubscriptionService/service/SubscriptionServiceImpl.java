package com.example.SubscriptionService.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.SubscriptionService.entity.Book;
import com.example.SubscriptionService.entity.Subscription;
import com.example.SubscriptionService.feign.BookRestConsumer;
import com.example.SubscriptionService.repository.SubscriptionRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	private static final String RESILIENCE4J_INSTANCE_NAME = "contactService";
	private static final String FALLBACK_METHOD = "getUserFallback";
	
	// creating a logger
    Logger logger
        = LoggerFactory.getLogger(SubscriptionServiceImpl.class);
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	//@LoadBalanced
	private RestTemplate restTemplate;
	
	@Autowired
    private DiscoveryClient discoveryClient;
	
	@Autowired
	private BookRestConsumer bookRestConsumer;

	@Override
	@CircuitBreaker(name = RESILIENCE4J_INSTANCE_NAME, fallbackMethod = FALLBACK_METHOD )
	public Subscription saveUser(Subscription subscription,String bookId) {
		List<ServiceInstance> instances = discoveryClient.getInstances("API-GATEWAY");        
		ServiceInstance instance =  instances.get(0);
		
		
		Book[] books = this.restTemplate.getForObject(instance.getUri() + "/getAvailableCopies/" + bookId , Book[].class);

		if(books.equals(0))
		{
			return null;
		}
		
		else {
			return subscriptionRepository.save(subscription);
		}
		
	}

	@Override
	public Subscription getUserWithFeign(String bookId) {
		List<Book> books = this.bookRestConsumer.getBooks(bookId);
		Subscription subscription = subscriptionRepository.findById(bookId).orElse(null);
		if(subscription != null) {
			subscription.setBooks(books);
		}
		
		return subscription;
	}
	
	public Subscription getSubscriptionFallback(String bookId, Throwable t) {
		logger.error("Book Service is not working!");
		return subscriptionRepository.findById(bookId).orElse(null);
	}



	@Override
	public List<Subscription> getAll() {
		// TODO Auto-generated method stub
		return subscriptionRepository.findAll();
	}

	@Override
	public List<Subscription> getSubscription(String subscriberName) {
		// TODO Auto-generated method stub
		return subscriptionRepository.findBySubscriberName(subscriberName);
	}

}
