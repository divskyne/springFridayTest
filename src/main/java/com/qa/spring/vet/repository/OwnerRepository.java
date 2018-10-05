package com.qa.spring.vet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.spring.vet.model.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long>
{
	Optional<Owner> findByName(String name);
}
