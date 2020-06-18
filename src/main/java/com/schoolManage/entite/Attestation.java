package com.schoolManage.entite;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Attestation {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long codeEtudiant;
	private String nomComplet;
	private String cin;
	private String cne;
	@Temporal(TemporalType.DATE)
	private Date date_naissance;
	private String ville_naissance;
	private String annee_session;
	private String type_diplome;
	private String annee_univers;
	private int nbr_telechargement=2;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean state_completion;
	
	@ManyToOne //@JsonIgnore
	private Etudiant etudiant;
	
	@ManyToOne //@JsonIgnore
	private Session session;
}
