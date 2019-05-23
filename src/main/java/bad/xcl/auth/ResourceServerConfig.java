package bad.xcl.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	//Reglas de Seguridad de nuestros recursos
	
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		/*http.authorizeRequests()
		.antMatchers("/ubicacion/**").permitAll() //Permitiendo todos a URL
		.antMatchers("/sala/**").hasRole("AH") //El solo agrega prefijo ROLE_
		.antMatchers("/especialidad/**").hasRole("AH")
		.antMatchers("/hospital/aprobados").hasRole("AG")
		.antMatchers("/hospital/pendientes").hasRole("AG")
		.antMatchers("/hospital/aprobar/**").hasRole("AG")
		.antMatchers("/hospital/crear").hasRole("AH")
		.antMatchers("/hospital/{id}").hasRole("AH")
		.antMatchers("/estado_civil/**").hasRole("AG")
		.antMatchers("/genero/**").hasRole("AG")
		.anyRequest().permitAll() //Todo lo demas permitidos, cambiar luego
		.and().cors().configurationSource(corsConfigurationSource());*/
		http.authorizeRequests().anyRequest().permitAll().and().cors().configurationSource(corsConfigurationSource());
	}
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
}
