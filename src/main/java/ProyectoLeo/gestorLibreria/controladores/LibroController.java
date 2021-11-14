/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoLeo.gestorLibreria.controladores;

import ProyectoLeo.gestorLibreria.entidades.Libro;
import ProyectoLeo.gestorLibreria.errores.errorServicio;
import ProyectoLeo.gestorLibreria.repositorios.RepositorioAutor;
import ProyectoLeo.gestorLibreria.repositorios.RepositorioEditorial;
import ProyectoLeo.gestorLibreria.repositorios.RepositorioLibro;
import ProyectoLeo.gestorLibreria.servicios.LibroServicio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    List<Libro> listadoLibros =  ls.listarLibros();  // ??  repo.findAll();
   //model.addAttribute("mensaje", "Listado de Libros");
   model.addAttribute("lista", listadoLibros);
    return "/lista";
}
 
@GetMapping("/buscar")
public String buscar ( Model model , Long id , String titulo, String autor  ){
    List<Libro> listadoLibros =  ls.listarLibros();
    for (Libro aux : listadoLibros) {
        if (aux.getId() == id || aux.getTitulo().equals(titulo) || aux.getAutor().equals(autor)) {
         model.addAttribute("libro" ,aux);         
    }
    }
return "/buscar";
}


//BUSCAR?? 
// Libro l = repo.buscarPorId(id);
//    model.addAttribute("libro" , l);

//    List<Libro> listadoLibros =  ls.listarLibros();
//    for (Libro aux : listadoLibros) {
//        if (aux.getId() == id) {
//         model.addAttribute("libro" , aux);     
//    }
//    }
 
    @PostMapping ("/editar/{id}")
public String editar(  ModelMap modelo, @PathVariable  Long id , @RequestParam String titulo,
        @RequestParam String autor, @RequestParam String editorial, 
        @RequestParam Integer anio ) throws errorServicio, Exception{
   
    try {
        ls.modificarLibro(id, titulo, autor, editorial, anio);
        modelo.put("exito","Se ha modificado" );
    return "lista";
    
    } catch (Exception e) {
        modelo.put("error","Falta algún dato" );
        e.printStackTrace();
         return "lista";
    } 
}

@GetMapping ("/editar/{id}")
public String editar(  ModelMap modelo, @PathVariable  Long id){
    
    
    modelo.addAttribute("libro" , repo.findById(id));
         return "editar";
    } 



@GetMapping(value="/borrar/{id}")
public String borrarLibro(Model model , @PathVariable Long id) throws errorServicio{
    ls.delete(id);
   return "redirect:/lista";
      
}


}
