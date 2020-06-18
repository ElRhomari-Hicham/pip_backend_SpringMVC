package com.schoolManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.schoolManage.entite.Attestation;
import com.schoolManage.entite.Etudiant;
import com.schoolManage.entite.Filiere;
import com.schoolManage.entite.SemestreFiliere;
import com.schoolManage.entite.Session;
import com.schoolManage.services.AttestationInitService;

@SpringBootApplication
public class SchoolManagementApplication implements CommandLineRunner{

	@Autowired AttestationInitService attestationInitService;
	@Autowired private RepositoryRestConfiguration restconfiguration;

	public static void main(String[] args) {
		SpringApplication.run(SchoolManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		restconfiguration.exposeIdsFor(Attestation.class, Etudiant.class, Filiere.class, SemestreFiliere.class, Session.class);
		
		attestationInitService.initFilieres();
		attestationInitService.initSessions();
		attestationInitService.initSemestreFilieres();
		attestationInitService.initEtudiants();
		attestationInitService.initAttestations();
	}

}
