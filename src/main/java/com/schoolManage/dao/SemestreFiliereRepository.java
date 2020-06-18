package com.schoolManage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.schoolManage.entite.SemestreFiliere;

@RepositoryRestResource
public interface SemestreFiliereRepository extends JpaRepository<SemestreFiliere, String> {

}
