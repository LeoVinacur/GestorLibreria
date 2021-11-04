/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoLeo.gestorLibreria.servicios;

import ProyectoLeo.gestorLibreria.entidades.Editorial;
import ProyectoLeo.gestorLibreria.errores.errorServicio;
import ProyectoLeo.gestorLibreria.repositorios.RepositorioEditorial;
 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author LEOPOLDO
 */
@Service
public class EditorialServicio {
    
    private RepositorioEditorial repoEd;
    
    @Transactional
     public Editorial ingresarEditorial (String nombre) throws errorServicio{
        
        Editorial editorial = new Editorial();
        
         if (nombre == null || nombre.isEmpty()) {
             throw new errorServicio ("El nombre no puede estar vacío");
         }
            
        editorial.setNombre(nombre);
        
        repoEd.save(editorial);
        
        return null;
    }
     
     @Transactional
     public void modificarEditorial (String nombre) throws errorServicio{
       if (nombre == null || nombre.isEmpty()) {
             throw new errorServicio ("El nombre no puede estar vacío");
         }
         
         Editorial editorial = repoEd.getById(nombre);
         editorial.setNombre(nombre);
         repoEd.save(editorial);
     }
   
     
     @Transactional
     public void borrarEditorial (String nombre) throws errorServicio{
          
            if (nombre == null || nombre.isEmpty()) {
             throw new errorServicio ("El nombre no puede estar vacío");
         }
         
         repoEd.deleteById(nombre);
            
     }
     
       @Transactional (readOnly = true)
     public void buscarEditorial (String nombre){
      
//      repoEd.buscarPorNombre(nombre);
  }
    
}
