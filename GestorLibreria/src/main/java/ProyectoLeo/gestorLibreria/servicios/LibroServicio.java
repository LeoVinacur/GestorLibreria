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
import org.springframework.transaction.annotation.Transactional;
 
/**
 *
 * @author LEOPOLDO
 */
@Service
public class LibroServicio {  
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    private AutorServicio autorService;
    private EditorialServicio editorialService;
 
    
    
    @Autowired
    private RepositorioLibro repositorioLibro;
 
    
    @Transactional 
    public void ingresarLibro (String titulo, String autor, String editorial, Integer anio ) throws Exception{
        
//            System.out.println("Ingrese título, autor, editorial y año del libro que quiere ingresar");
//                    String titulo = leer.next();
//                    String autor = leer.next();
//                    String editorial = leer.next();
//                    Integer anio = leer.nextInt();
        
            Libro libronuevo = new Libro();
            Autor nuevoAutor = autorService.ingresarAutor(autor);
            Editorial nuevaEditorial = editorialService.ingresarEditorial(editorial);
       
            
        try {
            
            libronuevo.setTitulo(titulo);
            libronuevo.setId(UUID.randomUUID().toString());
           // libronuevo.setISBN(UUID.randomUUID().getLeastSignificantBits());
            libronuevo.setIsbn((long) (int) (Math.random() * 999999 + 1));
            libronuevo.setAlta(Boolean.TRUE);
            libronuevo.setAutor(nuevoAutor);
            libronuevo.setEditorial(nuevaEditorial);
            libronuevo.setAnio(anio);
      
        
        repositorioLibro.save(libronuevo);
            
        } catch (Exception e) {
        System.out.println("Revise los valores ingresados");
        e.printStackTrace();
        }
       
    }
    
    @Transactional
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
    
    @Transactional
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
    
    @Transactional (readOnly = true)
    public List<Libro> listarLibros (){
        return repositorioLibro.findAll();
    }
           
  public void buscarLibro (String titulo){
      
      repositorioLibro.buscarPorTitulo(titulo);
  }
    
}
