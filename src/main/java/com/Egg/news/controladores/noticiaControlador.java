/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Egg.news.controladores;

import com.Egg.news.servicios.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import static sun.security.jgss.GSSUtil.login;

/**
 *
 * @author PC
 */
@Controller
@RequestMapping("/")
public class noticiaControlador {
    @Autowired
    private NoticiaServicio NoticiaServicio;
    
    @GetMapping("/")
    public String Inicio(){
        return "Index.html";
    }
  
    
}
