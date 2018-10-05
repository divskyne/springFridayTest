package com.qa.spring.vet.repo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.spring.vet.model.Owner;
import com.qa.spring.vet.repository.OwnerRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepoTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private OwnerRepository myRepo;
	
	@Test
	public void retrieveByIDTest() {
		Owner model1 = new Owner("Bob", "Space");
		entityManager.persist(model1);
		entityManager.flush();
		assertTrue(myRepo.findById(model1.getId()).isPresent());
	}
	
	@Test
	public void retrieveByNameTest() {
		Owner model1 = new Owner("Marley", "Space");
		entityManager.persist(model1);
		entityManager.flush();
		assertTrue(myRepo.findByName(model1.getName()).isPresent());
	}
}