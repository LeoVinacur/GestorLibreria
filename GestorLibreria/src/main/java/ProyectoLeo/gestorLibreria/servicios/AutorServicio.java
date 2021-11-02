/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoLeo.gestorLibreria.servicios;

import ProyectoLeo.gestorLibreria.entidades.Autor;
import ProyectoLeo.gestorLibreria.errores.errorServicio;
import ProyectoLeo.gestorLibreria.repositorios.RepositorioAutor;
import org.springframework.stereotype.Service;

/**
 *
 * @author LEOPOLDO
 */
@Service
public class AutorServicio {
    
     private RepositorioAutor repoAutor;
    
     public void ingresarAutor (String nombre) throws Exception{
        
        Autor autor = new Autor();
        
         if (nombre == null || nombre.isEmpty()) {
             throw new errorServicio ("El nombre no puede estar vacío");
         }
            
        autor.setNombre(nombre);
        
        repoAutor.save(autor);
        
    }
     
       public void modificarEditorial (String nombre) throws errorServicio{
       if (nombre == null || nombre.isEmpty()) {
             throw new errorServicio ("El nombre no puede estar vacío");
         }
         
         Autor autor = repoAutor.getById(nombre);
         autor.setNombre(nombre);
         repoAutor.save(autor);
     }
   
     
     public void borrarAutor (String nombre) throws errorServicio{
          
            if (nombre == null || nombre.isEmpty()) {
             throw new errorServicio ("El nombre no puede estar vacío");
         }
         
         repoAutor.deleteById(nombre);
            
     }
     
     public void buscarAutor (String nombre){
      
      repoAutor.buscarPorNombre(nombre);
  }
    
    
}
