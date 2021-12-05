 
package ProyectoLeo.gestorLibreria.seguridad;
import ProyectoLeo.gestorLibreria.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
 @EnableGlobalMethodSecurity(prePostEnabled = true)
public class SeguridadConfiguracion  extends WebSecurityConfigurerAdapter   {
     
     
          @Override
    protected void configure(HttpSecurity http) throws Exception {
        
      
    
        http
                .authorizeRequests()
                .antMatchers("/css/*", "/js/*", "/img/*",
                        "/**").permitAll()
                .and().
                formLogin()
                .loginPage("/") 
                .loginProcessingUrl("/logincheck")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/inicio")
                .permitAll()
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")   //login?logout          
                .permitAll().
                and().csrf().disable();
         
    }
         BCryptPasswordEncoder bCryptPasswordEncoder;
         
         //SEGUN VIDEO de YOUTUBE
    //Crea el encriptador de contraseñas	
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
//El numero 4 representa que tan fuerte quieres la encriptacion.
//Se puede en un rango entre 4 y 31. 
//Si no pones un numero el programa utilizara uno aleatoriamente cada vez
//que inicies la aplicacion, por lo cual tus contrasenas encriptadas no funcionaran bien
        return bCryptPasswordEncoder;
    }
	
   @Autowired
     public UsuarioServicio usuarioServicio;
     
//     //ADRIANA me hizo comentar esto porque dice que esta repetido (en el main)  
     @Autowired
     public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception {
         auth.userDetailsService(usuarioServicio).passwordEncoder(new BCryptPasswordEncoder());
     }
     
        
    //Segun un video , lo de abajo
//     String[] resources = new String[]{
//            "/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**"
//    };
     
 
     //SEGUN VIDEO de YOUTUBE
//        http
//            .authorizeRequests()
//	     
//	        .antMatchers("/","/index").permitAll()
//	        .antMatchers("/admin*").access("hasRole('ADMIN')")
//	        .antMatchers("/user*").access("hasRole('USER') or hasRole('ADMIN')")
//                .anyRequest().authenticated()
//                .and()
//            .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .defaultSuccessUrl("/inicio")
//                .failureUrl("/login?error=true")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .and()
//            .logout()
//                .permitAll()
//                .logoutSuccessUrl("/login?logout");
//    }
}

    
    



