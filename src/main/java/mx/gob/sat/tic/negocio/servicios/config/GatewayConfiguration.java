package mx.gob.sat.tic.negocio.servicios.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
public class GatewayConfiguration 
extends ResourceServerConfigurerAdapter 
{
	
	private static final String SERVER_RESOURCE_ID = "75f0fa18-64d6-45bf-abf2-2c84797f4fa1";
        
    @Override
    public void configure(final HttpSecurity http) throws Exception {
    http
    .csrf().disable()
    .authorizeRequests()
          .antMatchers("/oauth/**").permitAll()
          //.antMatchers("/covol/api/**").permitAll()
          .antMatchers("/**").authenticated()
          .and().httpBasic().disable()
          
//				.and().oauth2ResourceServer().jwt()
				;
    }
    
    
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
        .resourceId(SERVER_RESOURCE_ID)
        ;
    }
    
}
