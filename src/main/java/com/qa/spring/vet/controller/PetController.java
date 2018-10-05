package com.qa.spring.vet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.qa.spring.vet.exception.ResourceNotFoundException;
import com.qa.spring.vet.model.Pet;
import com.qa.spring.vet.repository.OwnerRepository;
import com.qa.spring.vet.repository.PetRepository;

@RestController
@RequestMapping("/owner")
public class PetController {
	
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private OwnerRepository ownerRepository;
	
	@GetMapping("/{ownerID}/pets")
	public Page<Pet> getAllOrdersByPersonId(@PathVariable (value="ownerID") Long ownerID, Pageable pageable)
	{
		return petRepository.findByOwnerId(ownerID, pageable);
	}
	
	@PostMapping("/{ownerID}/pets")
	public Pet createComment(@PathVariable (value="ownerID") Long personId,
			@Valid@RequestBody Pet pet)
	{
		return ownerRepository.findById(personId).map(ownerRepository -> {pet.setOwner(ownerRepository);
		return petRepository.save(pet);}).orElseThrow(() -> new ResourceNotFoundException("Person", "id", pet));
	}
}
