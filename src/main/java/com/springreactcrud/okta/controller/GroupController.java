package com.springreactcrud.okta.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springreactcrud.okta.model.Group;
import com.springreactcrud.okta.repository.GroupRepository;

@RestController
@RequestMapping("/v1/group")
public class GroupController {
	
	private final Logger log = LoggerFactory.getLogger(GroupController.class);
	private GroupRepository groupRepository;
	
	public GroupController(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}
	
	@GetMapping("/all")
	Collection<Group> groups() {
		return groupRepository.findAll();
	}
	
	@GetMapping("/{id}")
	ResponseEntity<?> getGroup(@PathVariable Long id) {
		Optional<Group> group = groupRepository.findById(id);
		return group.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping
	ResponseEntity<Group> createGroup(@Validated @RequestBody Group group) throws URISyntaxException {
		log.info("Request to create group: {}", group);
		Group result = groupRepository.save(group);
		return ResponseEntity.created(new URI("/v1/group/"+result.getId())).body(result);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Group> updateGroup(@Validated @RequestBody Group group) {
		log.info("Request to update group: {}", group);
		Group result = groupRepository.save(group);
		return ResponseEntity.ok().body(result);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
		log.info("Request to delete group: {}", id);
		groupRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	

}
