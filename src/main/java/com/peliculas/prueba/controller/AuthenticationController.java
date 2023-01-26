package com.peliculas.prueba.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.peliculas.prueba.dto.JwtRequest;
import com.peliculas.prueba.dto.JwtResponse;
import com.peliculas.prueba.exception.UsuarioNotFoundException;
import com.peliculas.prueba.model.Usuario;
import com.peliculas.prueba.service.impl.UserDetailsServiceImpl;
import com.peliculas.prueba.util.JwtUtil;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtils;
    
    private static final Logger logger= LoggerFactory.getLogger(AuthenticationController.class);	

    //generar token
    @PostMapping(value = "/generate-token")
    public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try{
            autenticar(jwtRequest.getUsername(),jwtRequest.getPassword());
        }catch (UsuarioNotFoundException exception){
            exception.printStackTrace();
            throw new Exception("Usuario no encontrado");
        }

        UserDetails userDetails =  this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void autenticar(String username,String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException exception){
            throw  new Exception("USUARIO DESHABILITADO " + exception.getMessage());
        }catch (BadCredentialsException e){
            throw  new Exception("Credenciales invalidas " + e.getMessage());
        }
    }

    @GetMapping(value = "/actual-usuario")
    public Usuario obtenerUsuarioActual(Principal principal) throws Exception {
        return (Usuario) this.userDetailsService.loadUserByUsername(principal.getName());
    }
    
    @PostMapping(value = "/validate-token")
    public ResponseEntity<?> validateToken(@RequestHeader(name = "Authorization") String token) {
        try {
            String username = jwtUtils.extractUsername(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtUtils.validateToken(token, userDetails)) {
                return ResponseEntity.ok("Token is valid");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Token is invalid");
        }
        return ResponseEntity.badRequest().body("Token is invalid");
    }
    
    @PostMapping(value = "/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) throws Exception {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String token = authHeader.substring(7);
            jwtUtils.invalidateToken(token);
            return ResponseEntity.ok("Logout successful");
        } else {
            return ResponseEntity.badRequest().body("No token found in request");
        }
    }
}