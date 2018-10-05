package com.qa.spring.vet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.spring.vet.model.Injury;

@Repository
public interface InjuriesRepository extends JpaRepository<Injury, Long>
{

}
