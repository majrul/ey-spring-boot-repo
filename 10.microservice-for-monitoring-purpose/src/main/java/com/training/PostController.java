package com.training;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private JsonPlaceHolderService jsonPlaceHolderService;
	
	@GetMapping()
	public List<Post> findAll() {
		return jsonPlaceHolderService.findAll();
	}

	@GetMapping("/{id}")
	public Post findById(@PathVariable int id) {
		return jsonPlaceHolderService.findById(id);
	}
}
