
package ProyectoLeo.gestorLibreria.repositorios;

import ProyectoLeo.gestorLibreria.entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author LEOPOLDO
 */
@Repository
public interface RepositorioLibro extends JpaRepository <Libro , String>{
  
    @Query ("SELECT l FROM Libro l WHERE l.titulo = :titulo")
    public Libro buscarPorTitulo (@Param("titulo") String titulo);
  
    
    @Query ("SELECT l FROM Libro l")
    public Libro listarLibros();
    
    @Override   // METODO USADO EN EL CONTROLER
       public List<Libro> findAll();
       
        
        // DA ERROR CUANDO CORRO EL PROGRAMA CON RUN 
 //      public void guardar(Libro libro);
//        public void eliminar(String Id);
//        public Libro buscar(String titulo);

    
}
