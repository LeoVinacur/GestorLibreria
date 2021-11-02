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

/**
 *
 * @author LEOPOLDO
 */
@Service
public class EditorialServicio {
    
    private RepositorioEditorial repoEd;
    
     public void ingresarEditorial (String nombre) throws errorServicio{
        
        Editorial editorial = new Editorial();
        
         if (nombre == null || nombre.isEmpty()) {
             throw new errorServicio ("El nombre no puede estar vacío");
         }
            
        editorial.setNombre(nombre);
        
        repoEd.save(editorial);
        
    }
     
     public void modificarEditorial (String nombre) throws errorServicio{
       if (nombre == null || nombre.isEmpty()) {
             throw new errorServicio ("El nombre no puede estar vacío");
         }
         
         Editorial editorial = repoEd.getById(nombre);
         editorial.setNombre(nombre);
         repoEd.save(editorial);
     }
   
     
     public void borrarEditorial (String nombre) throws errorServicio{
          
            if (nombre == null || nombre.isEmpty()) {
             throw new errorServicio ("El nombre no puede estar vacío");
         }
         
         repoEd.deleteById(nombre);
            
     }
     
     public void buscarEditorial (String nombre){
      
      repoEd.buscarPorNombre(nombre);
  }
    
}
