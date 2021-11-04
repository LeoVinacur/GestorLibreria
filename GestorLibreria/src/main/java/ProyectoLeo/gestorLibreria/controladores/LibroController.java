/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoLeo.gestorLibreria.controladores;

import ProyectoLeo.gestorLibreria.entidades.Libro;
import ProyectoLeo.gestorLibreria.repositorios.RepositorioLibro;
import ProyectoLeo.gestorLibreria.servicios.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author LEOPOLDO
 */
@Controller
public class LibroController {
    
    @Autowired
    private RepositorioLibro repo;
    
    @Autowired
    private LibroServicio ls;
    
    @GetMapping("/")
public String index(Model model){
    
  
return "index";
}

@GetMapping("/crearIndex/")
public String crear(){
    
    return "crearIndex" ;
}

@PostMapping("/save")
public String guardar(@ModelAttribute Libro libro){
  
    return "index";
}



}
