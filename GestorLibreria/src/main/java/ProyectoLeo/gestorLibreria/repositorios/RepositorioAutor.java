/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoLeo.gestorLibreria.repositorios;

import ProyectoLeo.gestorLibreria.entidades.Autor;
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
public interface RepositorioAutor extends JpaRepository <Autor , String> {
    
     @Query ("SELECT l.autor FROM Libro l WHERE l.autor = :nombre")
    public Autor buscarPorNombre (@Param("titulo") String nombre);
    
    @Query ("SELECT l.autor FROM Libro l")
    public Autor listarAutores ();
    
    @Override public List<Autor> findAll();
    
    
}
