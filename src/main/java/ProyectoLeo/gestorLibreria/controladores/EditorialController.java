/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoLeo.gestorLibreria.controladores;

import ProyectoLeo.gestorLibreria.repositorios.RepositorioEditorial;
import ProyectoLeo.gestorLibreria.repositorios.RepositorioLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author LEOPOLDO
 */
@Controller
public class EditorialController {
    
     @Autowired
    private RepositorioLibro repositorioLibro;
    
    @Autowired
    private RepositorioEditorial repoEd;
    
}
