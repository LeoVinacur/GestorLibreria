/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoLeo.gestorLibreria.controladores;

 
import ProyectoLeo.gestorLibreria.entidades.Usuario;
import ProyectoLeo.gestorLibreria.errores.errorServicio;
import ProyectoLeo.gestorLibreria.repositorios.RepositorioAutor;
import ProyectoLeo.gestorLibreria.repositorios.RepositorioEditorial;
import ProyectoLeo.gestorLibreria.repositorios.RepositorioLibro;
import ProyectoLeo.gestorLibreria.repositorios.RepositorioUsuario;
import ProyectoLeo.gestorLibreria.servicios.LibroServicio;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author 54261
 */
@Controller
public class PortalController {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @Autowired
    private RepositorioUsuario usuarioRepo;
   
 
    @PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
 @GetMapping("/usuarios")
    public String usuarios(ModelMap modelo) {
        List<Usuario> usuariosActivos = usuarioServicio.todosLosUsuarios();
        //Recordar que utilizo el modelo,para viajar con la llave usuarios al HTML la lista usuariosactivos
        modelo.addAttribute("usuarios", usuariosActivos);
        return "/usuarios";
    }
  
     @PostMapping("/logincheck")
    public String login (Model model){
      model.addAttribute("usuario" , new Usuario());
      return "login";
    }
    
  @GetMapping(" /logincheck")
public String index (ModelMap model , @RequestParam (required = false) String error ,  @RequestParam (required = false) String logout ){
  if(error != null){
    model.put("error" , "Hubo un problema en el login" );
      System.out.println(error);
    return "inicio";
    
  }
        if (logout != null) {
        model.put("logout" , "Ud. ha salido correctamente");     
        }
return "index";
}
//
    
       @PostMapping("/registrar")
    public String registrar(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave1, @RequestParam String clave2 ) throws errorServicio {

        try {
            usuarioServicio.registrar(nombre, apellido, mail, clave1, clave2);
        } catch (errorServicio error) {
            modelo.put("error" , error.getMessage());
            modelo.put ("nombre" , nombre);
            modelo.put ("apellido" , apellido);
            modelo.put ("mail" , mail);
            return "index";
        }
        
        modelo.put("titulo", "Bienvenido al Gestor de Librería,  "
                + nombre + " " + apellido +  ".   Tu usuario fue registrado de manera satisfactoria");  
        return"inicio";
    }
 
        
    @GetMapping({"/"})
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap modelo) {
        if (error != null) {
            modelo.put("error", "Usuario o clave incorrectos");
          return "index"; 
        }
        if (logout != null) {
            modelo.put("logout", "Ha salido correctamente.");
        }
        return "index";
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
   @PostMapping ("/editarUsuario/{mail}")
public String editar(  ModelMap modelo,  @RequestParam String nombre,
        @RequestParam String apellido, @RequestParam String mail, 
        @RequestParam String clave1 ,  @RequestParam String clave2 ) throws errorServicio, Exception{
   
    try {
        usuarioServicio.modificar(  nombre , apellido , mail , clave1 , clave2); 
        modelo.put("exito","Se ha modificado" );
    return "redirect:/usuarios";
    
    } catch (errorServicio error) {
            modelo.put("error" , error.getMessage());
            modelo.put ("nombre" , nombre);
            modelo.put ("apellido" , apellido);
            modelo.put ("mail" , mail);
         return "usuarios";
    } 
}

@GetMapping ("/editarUsuario/{mail}")
public String editar(  ModelMap modelo, @PathVariable  String mail){
    
     
    modelo.addAttribute("usuario" , usuarioRepo.buscarPorMail(mail));
    
         return "editarUsuario";
    } 



}
