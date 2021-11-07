/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoLeo.gestorLibreria.servicios;

import ProyectoLeo.gestorLibreria.entidades.Autor;
import ProyectoLeo.gestorLibreria.errores.errorServicio;
import ProyectoLeo.gestorLibreria.repositorios.RepositorioAutor;
import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author LEOPOLDO
 */
@Service
public class AutorServicio {
    
    @Autowired
     private RepositorioAutor repoAutor;
    
//    @Autowired
//    private Autor autor = new Autor();

    @Transactional
     public Autor ingresarAutor (String nombre) throws Exception{
        
       Autor autor = new Autor();
        
         if (nombre == null || nombre.isEmpty()) {
             throw new errorServicio ("El nombre no puede estar vacío");
         }
            
        autor.setNombre(nombre);
        autor.setAlta (true);
        
        repoAutor.save(autor);
        
        
        return null;
        
    }
     
     @Transactional
       public void modificarAutor (String nombre) throws errorServicio{
       if (nombre == null || nombre.isEmpty()) {
             throw new errorServicio ("El nombre no puede estar vacío");
         }
         
         Autor autor = repoAutor.getById(nombre);
         autor.setNombre(nombre);
         repoAutor.save(autor);
     }
   
     
       @Transactional
     public void borrarAutor (String nombre) throws errorServicio{
          
            if (nombre == null || nombre.isEmpty()) {
             throw new errorServicio ("El nombre no puede estar vacío");
         }
         
         repoAutor.deleteById(nombre);
            
     }
     
//       @Transactional (readOnly = true)
//     public void buscarAutor (String nombre){
//      
////      repoAutor.buscarPorNombre(nombre);
//  }
    
    
}
