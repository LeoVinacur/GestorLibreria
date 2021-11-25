/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoLeo.gestorLibreria.controladores;

 
import ProyectoLeo.gestorLibreria.entidades.Usuario;
import ProyectoLeo.gestorLibreria.errores.errorServicio;
import ProyectoLeo.gestorLibreria.servicios.UsuarioServicio;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author 54261
 */
@Controller
public class PortalController {
     @Autowired
    private UsuarioServicio usuarioServicio;
 
 @GetMapping("/usuarios")
    public String usuarios(ModelMap modelo) {
        List<Usuario> usuariosActivos = usuarioServicio.todosLosUsuarios();
        //Recordar que utilizo el modelo,para viajar con la llave usuarios al HTML la lista usuariosactivos
        modelo.addAttribute("usuarios", usuariosActivos);
        return "/usuarios";
    }
 

   // @PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
//    @GetMapping("/inicio")
//    public String inicio() {
//        return "redirect:/inicio";
//    }
    
       @PostMapping("/registrar")
    public String registrar(ModelMap modelo,   @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave1, @RequestParam String clave2 ) throws errorServicio {

        try {
            usuarioServicio.registrar(  nombre, apellido, mail, clave1, clave2);
        } catch (errorServicio error) {
            modelo.put("error" , error.getMessage());
            modelo.put ("nombre" , nombre);
            modelo.put ("apellido" , apellido);
            modelo.put ("mail" , mail);
            return "index";
        }
        
        modelo.put("titulo", "Bienvenido al Gestor de Librería de Leopoldo Vinacur ,"
                + " Tu usuario fue registrado de manera satisfactoria");
        
       // modelo.put("descripcion", "Tu usuario fue registrado de manera satisfactoria");
        return"inicio";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) {
        if (error != null) {
            model.put("error", "Usuario o clave incorrectos");
        }
        if (logout != null) {
            model.put("logout", "Ha salido correctamente.");
        }
        return "redirect:/inicio";
    }
    
    

    @GetMapping("/registro")
    public String registro(ModelMap modelo) {
       
        return "registro.html";
    }
    
    @GetMapping(value="/borrarUsuario/{id}")
public String borrarLibro(Model model , @PathVariable String id) throws errorServicio{
    usuarioServicio.borrar(id);
   return "redirect:/usuarios";
      
}

//     @GetMapping("/logincheck")
//    public String registrar(ModelMap modelo,   String mail, @RequestParam String password  ) throws errorServicio {
//
//        
//            if (usuarioServicio.loadUserByUsername(mail).getPassword().equals(password)) {
//             return "/inicio";   
//            }else  
//         
//            modelo.put("error" , "Usuario y/o contraseña incorrectos");
//            return "index";
//        
//         
//    }

}
