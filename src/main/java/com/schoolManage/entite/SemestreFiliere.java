package com.schoolManage.entite;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class SemestreFiliere {

	@Id
	private String semestreFiliereLibelle;
	
	@ManyToOne @JsonIgnore
	private Filiere filiere; 
	
	@ManyToOne @JsonIgnore
	private Session session;
	
}
