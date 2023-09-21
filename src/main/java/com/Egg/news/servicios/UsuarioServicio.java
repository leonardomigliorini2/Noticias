/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Egg.news.servicios;

import com.Egg.news.Enumeraciones.Rol;
import com.Egg.news.MiExcepciones.MisExcepciones;
import com.Egg.news.entidades.Usuario;
import com.Egg.news.repositorios.UsuarioRepositorio;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio implements UserDetailsService{

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void crearUsuario(String nombreUsuario, String email, String password, String password2) throws MisExcepciones {
        validar(nombreUsuario, email, password, password2);
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setPassword2(password2);
        usuario.setEmail(email);
        Date fechaAlta=new Date();
        SimpleDateFormat formatoFecha=new SimpleDateFormat("dd/mm/aa");
        usuario.setFechaAlta(fechaAlta);
        usuario.setActivo(true);
        usuario.setRol(Rol.USER);
        usuarioRepositorio.save(usuario);
    }
    
    public void validar(String nombreUsuario, String email, String password, String password2) throws MisExcepciones {
        if (nombreUsuario == null || nombreUsuario.isEmpty()) {
            throw new MisExcepciones("el usuario no puede ser nulo o estar vacio");
        }
        if (email == null || email.isEmpty()) {
            throw new MisExcepciones("el email no puede ser nulo o estar vacio");
        }
        if (password == null || password.isEmpty() || password.length() <= 5) {
            throw new MisExcepciones("la contraseña no puede ser nula o estar vacia, y debe tener al menos 5 caracteres");
        }
        if (! password.equalsIgnoreCase(password2)) {
            throw new MisExcepciones("las contraseñas deben ser iguales");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("funciona?");
        Usuario usuario = usuarioRepositorio.buscarPorEmail(email);
        if (usuario != null) {
            System.out.println("pasa algo?");
            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("Role_" + usuario.getRol().toString());
            permisos.add(p);
            return new User(usuario.getEmail(), usuario.getPassword(), permisos);
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }
}
