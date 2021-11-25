/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoLeo.gestorLibreria.servicios;
import ProyectoLeo.gestorLibreria.entidades.Usuario;
import ProyectoLeo.gestorLibreria.errores.errorServicio;
import ProyectoLeo.gestorLibreria.repositorios.RepositorioUsuario;
import java.util.List;
import java.util.Optional;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private  RepositorioUsuario usuarioRepositorio;

   
    @Transactional
    public void registrar(  String nombre, String apellido, String mail, String clave, String clave2 ) throws errorServicio  {

       
     validar(nombre, apellido, mail, clave, clave2 );

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setMail(mail);
       

        String encriptada = new BCryptPasswordEncoder().encode(clave);
        usuario.setClave(encriptada);
        
        usuarioRepositorio.save(usuario);

    }

    @Transactional
    public void modificar(  String id, String nombre, String apellido, String mail, String clave, String clave2 ) throws errorServicio {

     
        
       validar(nombre, apellido, mail, clave, clave2 );

        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Usuario usuario = respuesta.get();
            usuario.setApellido(apellido);
            usuario.setNombre(nombre);
            usuario.setMail(mail);
         
            String encriptada = new BCryptPasswordEncoder().encode(clave);
            usuario.setClave(encriptada);

         

            usuarioRepositorio.save(usuario);
        } else {

            
        }

    }

//    @Transactional
//    public void deshabilitar(String id) throws ErrorServicio {
//
//        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
//        if (respuesta.isPresent()) {
//
//            Usuario usuario = respuesta.get();
//            usuario.setBaja(new Date());
//            usuarioRepositorio.save(usuario);
//        } else {
//
//            throw new ErrorServicio("No se encontró el usuario solicitado");
//        }
//
//    }
//
//    @Transactional
//    public void habilitar(String id) throws ErrorServicio {
//
//        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
//        if (respuesta.isPresent()) {
//
//            Usuario usuario = respuesta.get();
//            usuario.setBaja(null);
//            usuarioRepositorio.save(usuario);
//        } else {
//
//            throw new ErrorServicio("No se encontró el usuario solicitado");
//        }
//
//    }

    public void validar(String nombre, String apellido, String mail, String clave, String clave2 ) throws errorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new errorServicio("El nombre del usuario no puede ser nulo");
        }

        if (apellido == null || apellido.isEmpty()) {
            throw new errorServicio("El apellido del usuario no puede ser nulo");
        }

        if (mail == null || mail.isEmpty()) {
            throw new errorServicio("El mail no puede ser nulo");
        }

        if (clave == null || clave.isEmpty() || clave.length() <= 6) {
            throw new errorServicio("La clave del usuario no puede ser nula y tiene que tener mas de seis digitos");
        }
        if (!clave.equals(clave2)) {
            throw new errorServicio("Las claves deben ser iguales");
        }

       
    }
//
//    @Override
//    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
//        Usuario usuario = usuarioRepositorio.buscarPorMail(mail);
//        if (usuario != null) {
//            List<GrantedAuthority> permisos = new ArrayList<>();
//                        
//            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_USUARIO_REGISTRADO");
//            permisos.add(p1);
//         
//            //Esto me permite guardar el OBJETO USUARIO LOG, para luego ser utilizado
//            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//            HttpSession session = attr.getRequest().getSession(true);
//            session.setAttribute("usuariosession", usuario);
//
//            User user = new User(usuario.getMail(), usuario.getClave(), permisos);
//            return user;
//
//        } else {
//            return null;
//        }
//
//    }
//
// @Transactional(readOnly=true)
//    public Usuario buscarPorId(String id) throws ErrorServicio {
//
//        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
//        if (respuesta.isPresent()) {
//
//            Usuario usuario = respuesta.get();
//            return usuario;
//        } else {
//
//            throw new ErrorServicio("No se encontró el usuario solicitado");
//        }
//
//    }
    
   @Transactional(readOnly=true)
    public List<Usuario> todosLosUsuarios(){
 
        return usuarioRepositorio.findAll();
        
    }

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void  login (String mail , String password){
         if ( loadUserByUsername(mail).getPassword().equals(password)) {
              
            }else  {
    }
  
}
    public void borrar (String mail){
        usuarioRepositorio.deleteById(mail);
    }

}