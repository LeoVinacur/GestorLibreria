/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoLeo.gestorLibreria.servicios;

import ProyectoLeo.gestorLibreria.entidades.Autor;
import ProyectoLeo.gestorLibreria.entidades.Editorial;
import ProyectoLeo.gestorLibreria.entidades.Libro;
import ProyectoLeo.gestorLibreria.errores.errorServicio;
import ProyectoLeo.gestorLibreria.repositorios.RepositorioLibro;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Scanner;
import javax.persistence.GenerationType;
import org.springframework.transaction.annotation.Transactional;
 

@Service
public class LibroServicio {  
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
   
     @Autowired
    private AutorServicio autorService;
     
      @Autowired
    private EditorialServicio editorialService;
 
    @Autowired
    private RepositorioLibro repositorioLibro;

// probando según el video     
    public void guardar (Libro libro){
        repositorioLibro.save(libro);
    }
  
    //probando segun el video
      public void encontrar (String id){
          repositorioLibro.findById(id).orElse(null);
      }
      
      //probando segun el video
      public void borrar (String id){
          repositorioLibro.deleteById(id);
      }
      
       public String buscarLibro (String titulo){
      
      repositorioLibro.buscarPorTitulo(titulo);
      
      return titulo;
  }
 
//    @Autowired
//   private Libro libronuevo = new Libro();
    
  
    @Transactional 
    public void ingresarLibro (String titulo, String autor, String editorial, Integer anio ) throws errorServicio, Exception {
        
           
            Autor nuevoAutor = autorService.ingresarAutor(autor);
            Editorial nuevaEditorial = editorialService.ingresarEditorial(editorial);
            Libro libronuevo = new Libro();
       
            validar (titulo , autor, editorial, anio);
            
            libronuevo.setIsbn((long) (int) (Math.random() * 999999 + 1));
            libronuevo.setTitulo(titulo);
          
            libronuevo.setAlta(true);
            libronuevo.setAutor(nuevoAutor);
            libronuevo.setEditorial(nuevaEditorial);
            libronuevo.setAnio(anio);
      
        
       repositorioLibro.save(libronuevo);
       
    }
    
    @Transactional
    public void modificarLibro  (String titulo , Autor autor , Editorial editorial, Integer anio)throws errorServicio{
        Libro libro = repositorioLibro.findById(titulo).get();
        
        
         if (titulo == null || titulo.isEmpty()) {
             throw new errorServicio ("El nombre no puede estar vacío");
         }
          if (autor == null) {
             throw new errorServicio ("El nombre no puede estar vacío");
         }
           if (editorial == null) {
             throw new errorServicio ("El nombre no puede estar vacío");
         }
            if (anio == null) {
             throw new errorServicio ("El nombre no puede estar vacío");
         }
      
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libro.setAnio(anio);
        
        repositorioLibro.save(libro);
     
    }
    
    @Transactional
    public void borrarLibro (String titulo) throws errorServicio  {
         Libro libro = repositorioLibro.findById(titulo).get();
           try {
         repositorioLibro.deleteById(titulo);
               System.out.println("Libro borrado");
         } catch (Exception e) {
              System.out.println("No se encuentra el libro");
        e.printStackTrace();
        }
    }
    
    @Transactional (readOnly = true)
    public List<Libro> listarLibros (){
    
  
        return repositorioLibro.findAll();
    }
           

  public void validar (String titulo, String autor, String editorial, Integer anio ) throws errorServicio {
      
    if (titulo == null || titulo.isEmpty()) {
             throw new errorServicio ("El nombre no puede estar vacío");
         }
         
          if (autor == null) {
             throw new errorServicio ("El nombre no puede estar vacío");
         }
           if (editorial == null) {
             throw new errorServicio ("El nombre no puede estar vacío");
         }
            if (anio == null) {
             throw new errorServicio ("El nombre no puede estar vacío");
         }
      
  }
 
    
}

