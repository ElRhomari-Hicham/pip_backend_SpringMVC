package com.schoolManage.entite;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Filiere {

	@Id
	private String filiereLibelle;
	
	@OneToMany(mappedBy = "filiere")
	@JsonProperty(access=Access.WRITE_ONLY)
	private Collection<SemestreFiliere> semestrefiliereList;
	
	@OneToMany(mappedBy = "filiere")
	@JsonProperty(access=Access.WRITE_ONLY)
	private Collection<Etudiant> etudiantList;
	
}
