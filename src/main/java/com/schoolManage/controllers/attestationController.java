package com.schoolManage.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.schoolManage.dao.AttestationRepository;
import com.schoolManage.entite.Attestation;

@RestController
public class attestationController {
	
	@Autowired
	private AttestationRepository attestationRepository;
	
	@GetMapping(path="/attestations")
	public List<Attestation> getAttestations(){
		return attestationRepository.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path="/attestation/{id}")
	public Attestation getAttestation(@PathVariable Long id) {
		return attestationRepository.findAttestationByCodeEtudiant(id);
	}
	
	@PostMapping(path="/attestation")
	public Attestation saveAttestation(@RequestBody Attestation attest) {
		return attestationRepository.save(attest);
	}
	
	@DeleteMapping(path="/attestation/{id}")
	public void deleteAttestation(@PathVariable Long id) {
		attestationRepository.deleteById(id);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping(path="/attestation/{id}")
	public Attestation updateAttestation(@RequestBody Attestation attest,@PathVariable Long id) {
		System.out.println("Updating : " + id);
		Attestation attestation = attestationRepository.findById(id).get();
		attestation.setId(attest.getId());
		attestation = attest;
		return attestationRepository.save(attestation);
	}

}
