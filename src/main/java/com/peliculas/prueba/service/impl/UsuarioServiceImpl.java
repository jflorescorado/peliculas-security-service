package com.peliculas.prueba.service.impl;

import java.util.Date;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peliculas.prueba.exception.UsuarioFoundException;
import com.peliculas.prueba.model.Usuario;
import com.peliculas.prueba.model.UsuarioRol;
import com.peliculas.prueba.repository.RolRepository;
import com.peliculas.prueba.repository.UsuarioRepository;
import com.peliculas.prueba.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RolRepository rolRepository;
	
	private static final Logger logger= LoggerFactory.getLogger(UsuarioServiceImpl.class);	

	@Override
	public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
		//Busqueda de usuario por username
		Usuario response = usuarioRepository.findByUsername(usuario.getUsername());
		//Validar si se encontro un usuario con ese username o no
		if(response != null) {
			//En caso de haber encontrado un usuario con ese username
			logger.error("El usuario " + usuario.getUsername() + " ya existe");
			throw new UsuarioFoundException("El usuario ya esta presente");
		}
		else {
			//En caso no existe un usuario con ese username
			for(UsuarioRol usuarioRol:usuarioRoles) {
				rolRepository.save(usuarioRol.getRol());
			}
			usuario.getUsuarioRoles().addAll(usuarioRoles);
			usuario.setFecha(new Date());
			//Se registra un nuevo usuario
			response = usuarioRepository.save(usuario);
		}
		return response;
	}


	@Override
	public Usuario obtenerUsuario(String username) {
		Usuario response = new Usuario();
		try {
			//Busqueda de usuario por username
			response = usuarioRepository.findByUsername(username);
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public void eliminarUsuario(Long usuarioId) {
		try {
			//Eliminar usuario por id
			usuarioRepository.deleteById(usuarioId);
		} catch (Exception e) {
			throw e;
		}
	}

}
