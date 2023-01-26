package com.peliculas.prueba.service;

import java.util.Set;

import com.peliculas.prueba.model.Usuario;
import com.peliculas.prueba.model.UsuarioRol;

public interface UsuarioService {

	public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;
	
	public Usuario obtenerUsuario(String username);
	
	public void eliminarUsuario(Long usuarioId);
}

