/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoLeo.gestorLibreria.repositorios;

import ProyectoLeo.gestorLibreria.entidades.Editorial;
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
public interface RepositorioEditorial extends JpaRepository <Editorial , String>{
//    
//     @Query ("SELECT l.editorial FROM Libro l WHERE l.editorial = :nombre")
//    public Editorial buscarPorNombre (@Param("titulo") String nombre);
//    
//    @Query ("SELECT l.editorial FROM Libro l")
//    public Editorial listarEditorial ();
//    
//    @Override
//        public List<Editorial> findAll();
    
}
