package com.qa.spring.vet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.spring.vet.exception.ResourceNotFoundException;
import com.qa.spring.vet.model.Injury;
import com.qa.spring.vet.model.Pet;
import com.qa.spring.vet.repository.InjuriesRepository;
import com.qa.spring.vet.repository.PetRepository;

@RestController
@RequestMapping("/injury")
public class InjuriesController
{
	@Autowired
	private InjuriesRepository injuriesRepository;
	
	@Autowired
	private PetRepository petRepository;
	
	@PostMapping("/")
	private Injury createInjury(@Valid @RequestBody Injury m) {
		return injuriesRepository.save(m);
	}
	
	@GetMapping("/{id}")
	public Injury getInjurybyID(@PathVariable(value="id")Long injuryID)
	{
		return injuriesRepository.findById(injuryID).orElseThrow(()->new ResourceNotFoundException("Model", "id", injuryID));
	}
	
	@PostMapping("/{id}/{id2}")
	private Pet createPetInjury(@PathVariable(value="id")Long petID, @PathVariable(value="id")Long injuryID) 
	{
		petRepository.findById(petID).get().setInjury(injuriesRepository.findById(injuryID).get());
		return petRepository.save(petRepository.findById(petID).get().getInjury());
	}
}