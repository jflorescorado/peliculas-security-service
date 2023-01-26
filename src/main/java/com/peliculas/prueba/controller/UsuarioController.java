package com.peliculas.prueba.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peliculas.prueba.model.Rol;
import com.peliculas.prueba.model.Usuario;
import com.peliculas.prueba.model.UsuarioRol;
import com.peliculas.prueba.service.UsuarioService;


@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping(value = "/")
	public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception {
		//encriptacion de password
		usuario.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));
		
		Set<UsuarioRol> roles = new HashSet<>();
		
		Rol rol = new Rol();
		rol.setIdRol(2L);
		rol.setNombre("NORMAL");
		rol.setFecCrea(new Date());
		
		UsuarioRol usuarioRol = new UsuarioRol();
		usuarioRol.setUsuario(usuario);
		usuarioRol.setRol(rol);
		
		 roles.add(usuarioRol);
		
		return usuarioService.guardarUsuario(usuario, roles);
	}
	
	@GetMapping(value = "/{username}")
	public Usuario obtenerUsuario(@PathVariable("username") String username) throws Exception {
		return usuarioService.obtenerUsuario(username);
	}
	
	@DeleteMapping(value = "/{usuarioId}")
	public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId) throws Exception {
		usuarioService.eliminarUsuario(usuarioId);
	}
}