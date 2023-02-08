package com.springreactcrud.okta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springreactcrud.okta.model.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
	Group findByName(String name);

}
