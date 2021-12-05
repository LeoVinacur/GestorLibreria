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
import ProyectoLeo.gestorLibreria.servicios.UsuarioServicio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
    private UsuarioServicio usuarioServicio;
    
    @Autowired
    private RepositorioLibro repo;
    
    @Autowired
    private LibroServicio ls;
    
     @Autowired
    private RepositorioEditorial repoEd;
     
     @Autowired
     private RepositorioAutor repoAutor;

    
  
 
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    @GetMapping("/inicio")
//    public String inicio() {
//        return "redirect:/inicio";
//    }

  @PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
  @GetMapping("/inicio")
    public String inicio() {
        return  "inicio";
    }

  
 @PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")   
@GetMapping("/crearIndex")
public String crear(){
   
    return "crearIndex" ;
}

@PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
@PostMapping("/crearIndex")
public String guardar(ModelMap modelo, @RequestParam String titulo, @RequestParam String autor, @RequestParam String editorial, @RequestParam Integer anio ) throws Exception{
   
    try {
        ls.ingresarLibro(titulo, autor, editorial, anio);
        modelo.put("exito","Registro exitoso" );
    return "/inicio";
    
    } catch (Exception e) {
        modelo.put("error","Falta algún dato" );
        e.printStackTrace();
         return "crearIndex";
    } 
}
  
 @PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
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
    ArrayList<Libro> busqueda = new ArrayList<Libro>() ;
    for (Libro aux : listadoLibros) {
        if (aux.getId() == id || aux.getTitulo().equalsIgnoreCase(titulo) || aux.getAutor().getNombre().equalsIgnoreCase(autor) ) {
            busqueda.add(aux);
            model.addAttribute("libro" ,busqueda);         
    }
    }
    
return "/buscar";
}


    @PostMapping ("/editar/{id}")
public String editar(  ModelMap modelo, @PathVariable  Long id , @RequestParam String titulo,
        @RequestParam String autor, @RequestParam String editorial, 
        @RequestParam Integer anio ) throws errorServicio, Exception{
   
    try {
        ls.modificarLibro(id, titulo, autor, editorial, anio);
        modelo.put("exito","Se ha modificado" );
    return "redirect:/lista";
    
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
public String borrarLibro(ModelMap model , @PathVariable Long id) throws errorServicio{
     model.put("error","Se ha borrado el libro" );
    ls.delete(id);
   return "redirect:/lista";
      
}

 
}
