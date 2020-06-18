package com.schoolManage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.schoolManage.entite.Attestation;

@RepositoryRestResource
public interface AttestationRepository extends JpaRepository<Attestation, Long>{
	
	@Query("select a from Attestation a where a.codeEtudiant=:x")
	public Attestation findAttestationByCodeEtudiant(@Param("x") Long id);
	
}
