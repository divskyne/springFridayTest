package com.qa.spring.vet.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.spring.vet.model.Injury;
import com.qa.spring.vet.model.Owner;
import com.qa.spring.vet.model.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long>
{
	Page<Pet> findByOwnerId(Long ownerID, Long petID, Pageable pageable);

	Page<Pet> findByOwnerId(Long ownerID, Pageable pageable);

	//Pet save(Set<Injury> injury);
}
