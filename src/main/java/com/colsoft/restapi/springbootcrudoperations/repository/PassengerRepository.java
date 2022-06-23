package com.colsoft.restapi.springbootcrudoperations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colsoft.restapi.springbootcrudoperations.model.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long>{

}
