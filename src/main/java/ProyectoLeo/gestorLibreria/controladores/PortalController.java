/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoLeo.gestorLibreria.controladores;

 
//import com.proyecto.demo.servicios.UsuarioServicio;
//import ProyectoLeo.gestorLibreria.entidades.Usuario;
//import ProyectoLeo.gestorLibreria.errores.errorServicio;
//import ProyectoLeo.gestorLibreria.servicios.UsuarioServicio;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author 54261
 */
//@Controller
//public class PortalController {
//     @Autowired
//    private UsuarioServicio usuarioServicio;
// 
//
////    @GetMapping()
////    public String index(ModelMap modelo) {
////        List<Usuario> usuariosActivos = usuarioServicio.todosLosUsuarios();
////        //Recordar que utilizo el modelo,para viajar con la llave usuarios al HTML la lista usuariosactivos
////        modelo.addAttribute("usuarios", usuariosActivos);
////        return "index.html";
////    }
//
//    @PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
//    @GetMapping("/inicio")
//    public String inicio() {
//        return "inicio.html";
//    }
//
//    @GetMapping("/login")
//    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) {
//        if (error != null) {
//            model.put("error", "Usuario o clave incorrectos");
//        }
//        if (logout != null) {
//            model.put("logout", "Ha salido correctamente.");
//        }
//        return "login.html";
//    }
//
//    @GetMapping("/registro")
//    public String registro(ModelMap modelo) {
//       
//        return "registro.html";
//    }
//
//    @PostMapping("/registrar")
//    public String registrar(ModelMap modelo,   @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave1, @RequestParam String clave2, @RequestParam String idZona) {
//
//        try {
//            usuarioServicio.registrar(  nombre, apellido, mail, clave1, clave2);
//        } catch (errorServicio e) {
//            
//            
//           
//            modelo.put("nombre", nombre);
//            modelo.put("apellido", apellido);
//            modelo.put("mail", mail);
//            modelo.put("clave1", clave1);
//            modelo.put("clave2", clave2);
//            return "registro.html";
//        }
//        modelo.put("titulo", "Bienvenido a Tinder de Mascotas");
//        modelo.put("descripcion", "Tu usuario fue registrado de manera satisfactoria");
//        return "exito.html";
//    }
//
//}
