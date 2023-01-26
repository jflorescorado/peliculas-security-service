package com.peliculas.prueba;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.peliculas.prueba.exception.UsuarioFoundException;
import com.peliculas.prueba.model.Rol;
import com.peliculas.prueba.model.Usuario;
import com.peliculas.prueba.model.UsuarioRol;
import com.peliculas.prueba.service.UsuarioService;


@SpringBootApplication
public class PeliculasSecurityServiceApplication implements CommandLineRunner{
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(PeliculasSecurityServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		/*
		 * try{
		 * 
		 * Usuario usuario = new Usuario(); usuario.setNombre("Admin");
		 * usuario.setApellido("Prueba"); usuario.setUsername("admin");
		 * usuario.setPassword(bCryptPasswordEncoder.encode("12345"));
		 * usuario.setEmail("adminprueba@gmail.com");
		 * usuario.setTelefono("+50361566918");
		 * 
		 * Rol rol = new Rol(); rol.setIdRol(1L); rol.setNombre("ADMIN");
		 * rol.setFecCrea(new Date());
		 * 
		 * Set<UsuarioRol> usuariosRoles = new HashSet<>();
		 * 
		 * UsuarioRol usuarioRol = new UsuarioRol(); usuarioRol.setRol(rol);
		 * usuarioRol.setUsuario(usuario); usuariosRoles.add(usuarioRol);
		 * 
		 * Usuario usuarioGuardado =
		 * usuarioService.guardarUsuario(usuario,usuariosRoles);
		 * 
		 * System.out.println(usuarioGuardado.getUsername());
		 * 
		 * }catch (UsuarioFoundException exception){ exception.printStackTrace(); }
		 */
	}
}
