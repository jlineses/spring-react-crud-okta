package com.springreactcrud.okta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springreactcrud.okta.model.User;

public interface UserRepository extends JpaRepository<User, String> {
}