package com.peliculas.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peliculas.prueba.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Usuario findByUsername(String username);
	

}

