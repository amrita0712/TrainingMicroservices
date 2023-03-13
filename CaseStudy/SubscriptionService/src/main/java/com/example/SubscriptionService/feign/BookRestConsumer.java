package com.example.SubscriptionService.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.SubscriptionService.entity.Book;


@FeignClient(name = "BookService", path="/books")
public interface BookRestConsumer {
	
	@GetMapping("{bookId}")
	List<Book> getBooks(@PathVariable("bookId") String bookId);
}
