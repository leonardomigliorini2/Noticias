
package com.Egg.news.controladores;


import com.Egg.news.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/seguridad")
public class NoticiaControlador2 {

    @Autowired
    private UsuarioServicio UsuarioServicio;

    @GetMapping("/")
    public String index() {
        return "Index.html";
    }

    @GetMapping("/registro")
    public String registrar() {

        return "registrar.html";
    }

    @PostMapping("/registrarUsuario")
    public String registro(@RequestParam String nombreUsuario, @RequestParam String email, @RequestParam String password, @RequestParam String password2, ModelMap modelo) {
        try {
            UsuarioServicio.crearUsuario(nombreUsuario, email, password, password2);
            System.out.println("estoy aca");
            modelo.put("exito", "el ususario se registro correctamente");

            return "registrar.html";
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            modelo.put("nombre", nombreUsuario);
            modelo.put("email", email);
            return "registrar.html";
        }

    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {
        if (error != null) {
            modelo.put("error", "usuario o contraseña inválidos");
        }

        return "login.html";

    }


}
