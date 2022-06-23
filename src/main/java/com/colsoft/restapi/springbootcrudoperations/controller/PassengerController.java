package com.colsoft.restapi.springbootcrudoperations.controller;

import java.util.List;

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

import com.colsoft.restapi.springbootcrudoperations.exception.ResourceNotFoundException;
import com.colsoft.restapi.springbootcrudoperations.model.Passenger;
import com.colsoft.restapi.springbootcrudoperations.repository.PassengerRepository;

@RestController
@RequestMapping("/api/v1")
public class PassengerController {
	
	@Autowired
	private PassengerRepository passengerRepository;
	
	@GetMapping("/passengers")
	public List<Passenger> getAllPassengers(){
		return passengerRepository.findAll();
	}
	
	@PostMapping("/passengers")
	public Passenger createPassenger(Passenger passenger) {
		return passengerRepository.save(passenger);
	}
	
	@GetMapping("/passengers/{id}")
	public ResponseEntity<Passenger> getPassengerById(@PathVariable(value = "id") long passengerId)
			throws ResourceNotFoundException {
		Passenger passenger = passengerRepository.findById(passengerId)
				.orElseThrow(() -> new ResourceNotFoundException("Pax not found for this id::" + passengerId));
		return ResponseEntity.ok().body(passenger);
	}
	
	@PutMapping("/passengers/{id}")
	public ResponseEntity<Passenger> updatePassenger(@PathVariable(value = "id") long passengerId
			,@RequestBody Passenger passenegrDetails) throws ResourceNotFoundException {
		Passenger passenger = passengerRepository.findById(passengerId)
				.orElseThrow(() -> new ResourceNotFoundException("Pax not found for this id::" + passengerId));
		passenger.setFirstname(passenegrDetails.getFirstname());
		passenger.setLastname(passenegrDetails.getLastname());
		passenger.setEmailId(passenegrDetails.getEmailId());
		passengerRepository.save(passenger);
		return ResponseEntity.ok().body(passenger);
	}
	
	@DeleteMapping("/passengers/{id}")
	public ResponseEntity<?> deletePassenger(@PathVariable(value = "id") long passengerId) throws ResourceNotFoundException {
		passengerRepository.findById(passengerId)
				.orElseThrow(() -> new ResourceNotFoundException("Pax not found for this id::" + passengerId));
		passengerRepository.deleteById(passengerId);
		return ResponseEntity.ok().build();
	}

}
