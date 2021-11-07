/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoLeo.gestorLibreria.controladores;

import ProyectoLeo.gestorLibreria.entidades.Libro;
import ProyectoLeo.gestorLibreria.repositorios.RepositorioAutor;
import ProyectoLeo.gestorLibreria.repositorios.RepositorioEditorial;
import ProyectoLeo.gestorLibreria.repositorios.RepositorioLibro;
import ProyectoLeo.gestorLibreria.servicios.LibroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author LEOPOLDO
 */
@Controller
@RequestMapping("/")  // localhost:8080/
public class LibroController {
    
    @Autowired
    private RepositorioLibro repo;
    
    @Autowired
    private LibroServicio ls;
    
     @Autowired
    private RepositorioEditorial repoEd;
     
     @Autowired
     private RepositorioAutor repoAutor;

    
    @GetMapping("/")
public String index(Model model){
    
  
return "index";
}

@GetMapping("/crearIndex")
public String crear(){
   
    return "crearIndex" ;
}

@PostMapping("/crearIndex")
public String guardar(ModelMap modelo, @RequestParam String titulo, @RequestParam String autor, @RequestParam String editorial, @RequestParam Integer anio ) throws Exception{
   
    try {
        ls.ingresarLibro(titulo, autor, editorial, anio);
        modelo.put("exito","Registro exitoso" );
    return "index";
    
    } catch (Exception e) {
        modelo.put("error","Falta algún dato" );
        e.printStackTrace();
         return "crearIndex";
    }
  
}

 
@GetMapping("/lista")
public String listarLibros(ModelMap model){
    List<Libro> listadoLibros = ls.listarLibros();  // ??  repo.findAll();
   model.addAttribute("mensaje", "Listado de Libros");
   model.addAttribute("lista", listadoLibros);
    return "/lista";
}

}
