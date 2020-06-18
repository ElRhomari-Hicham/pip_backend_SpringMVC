package com.schoolManage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.schoolManage.entite.Filiere;

@RepositoryRestResource
public interface FiliereRepository extends JpaRepository<Filiere, String>{

}
