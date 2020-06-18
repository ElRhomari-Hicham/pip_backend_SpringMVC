package com.schoolManage.entite;

import java.util.Date;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Etudiant {
	
	@Id
	private Long id;
	private String firstname;
	private String lastname;
	@NotNull @NotEmpty
	@Size(min=7, max=9)
	private String cni;
	@NotNull @NotEmpty
	@Size(min=9, max=11)
	private String cne;
	private String sex;
	@Temporal(TemporalType.DATE)
	private Date date_naissance;
	@NotEmpty
	private String ville_naissance;
	@NotNull @NotEmpty
	@Email
	private String email;
	private String infos;
	
	@ManyToOne @JsonIgnore
	private Filiere filiere;
	
	@OneToMany(mappedBy = "etudiant")
	@JsonProperty(access=Access.WRITE_ONLY)
	private Collection<Attestation> attestationList;

}
