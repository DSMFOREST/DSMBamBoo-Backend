package kim.jaehoon.dsmforest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@SpringBootApplication
public class DsmforestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DsmforestApplication.class, args);
    }

    @Configuration
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    @EnableWebSecurity
    static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL);
            corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
            corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", corsConfiguration);

            httpSecurity.httpBasic()
                    .and().authorizeRequests()
                    .anyRequest().permitAll()
                    .and().cors().configurationSource(source)
                    .and().csrf().disable();
        }
    }

}
