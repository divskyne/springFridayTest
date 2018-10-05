package com.qa.spring.vet.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.spring.vet.exception.ResourceNotFoundException;
import com.qa.spring.vet.model.Owner;
import com.qa.spring.vet.repository.OwnerRepository;

@RestController
@RequestMapping("/owner")
public class OwnerController {
	
	@Autowired
	private OwnerRepository ownerRepository;
	
	@GetMapping("/")
	public List<Owner> getAllPeople()
	{
		return ownerRepository.findAll();
	}
	
	@PostMapping("/")
	private Owner createOwner(@Valid @RequestBody Owner m) {
		return ownerRepository.save(m);
	}
	
	@GetMapping("/{id}")
	public Owner getOwnerbyID(@PathVariable(value="id")Long ownerID)
	{
		return ownerRepository.findById(ownerID).orElseThrow(()->new ResourceNotFoundException("Model", "id", ownerID));
	}
	
	@PutMapping("/{id}")
	public Owner updatePerson(@PathVariable(value="id")Long ownerID,
			@Valid@RequestBody Owner personDetails)
	{
		Owner e = ownerRepository.findById(ownerID).orElseThrow(()->new ResourceNotFoundException("Model", "id", ownerID));
		
		e.setName(personDetails.getName());
		e.setAddress(personDetails.getAddress());
		
		Owner updateData = ownerRepository.save(e);
		return updateData;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable(value="id")Long ownerID)
	{
		Owner e = ownerRepository.findById(ownerID).orElseThrow(()->new ResourceNotFoundException("Model", "id", ownerID));
		
		ownerRepository.delete(e);
		return ResponseEntity.ok().build();
	}

}
