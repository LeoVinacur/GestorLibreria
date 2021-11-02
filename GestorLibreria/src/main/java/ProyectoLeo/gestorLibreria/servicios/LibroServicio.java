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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LEOPOLDO
 */
@Service
public class LibroServicio {  
    
    @Autowired
    private RepositorioLibro repositorioLibro;
    
    public void ingresarLibro (String titulo , Autor autor , Editorial editorial, Integer anio , Boolean alta) throws Exception{
        
        Libro libro = new Libro();
        
        try {
            
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libro.setAnio(anio);
        libro.setAlta(alta);
        
        repositorioLibro.save(libro);
            
        } catch (Exception e) {
        System.out.println("Revise los valores ingresados");
        e.printStackTrace();
        }
       
    }
    
    public void modificarLibro  (String titulo , Autor autor , Editorial editorial, Integer anio) throws Exception{
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
    
    public void borrarLibro (String titulo) throws Exception {
         Libro libro = repositorioLibro.findById(titulo).get();
           try {
         repositorioLibro.deleteById(titulo);
               System.out.println("Libro borrado");
         } catch (Exception e) {
              System.out.println("No se encuentra el libro");
        e.printStackTrace();
        }
    }
    
    public List<Libro> listarLibros (){
        return repositorioLibro.findAll();
    }
           
  public void buscarLibro (String titulo){
      
      repositorioLibro.buscarPorTitulo(titulo);
  }
    
}
