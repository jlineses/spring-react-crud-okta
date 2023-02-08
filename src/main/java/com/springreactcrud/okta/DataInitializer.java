package com.springreactcrud.okta;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.springreactcrud.okta.model.Event;
import com.springreactcrud.okta.model.Group;
import com.springreactcrud.okta.repository.GroupRepository;

@Component
public class DataInitializer implements CommandLineRunner {
	
	private final GroupRepository repository;
	
	public DataInitializer(GroupRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(String... args) throws Exception {
		Stream.of("Seattle", "Denver", "Manila", "Cebu")
		.forEach(name -> repository.save(new Group(name)));
	
		Group g = repository.findByName("Manila");
		Event e = Event.builder()
				.title("Manila")
				.description("I'm coming home to Manila")
				.date(Instant.parse("2022-09-13T17:00:00.000Z"))
				.build();
		g.setEvents(Collections.singleton(e));
		repository.save(g);
		repository.findAll().forEach(System.out::println);
	}

}
