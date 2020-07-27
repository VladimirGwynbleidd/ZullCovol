package mx.gob.sat.tic.negocio.servicios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableZuulProxy
@EnableHystrix
@EnableResourceServer
public class MsSatZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsSatZuulApplication.class, args);
	}

}
