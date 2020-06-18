package com.schoolManage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.schoolManage.entite.Session;

@RepositoryRestResource
public interface SessionRepository extends JpaRepository<Session, String>{

}
