package com.peliculas.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peliculas.prueba.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long>{
	
}
