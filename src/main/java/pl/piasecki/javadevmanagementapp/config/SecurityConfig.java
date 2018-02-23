package pl.piasecki.javadevmanagementapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
@Profile("Production")
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/lectures").hasRole("STUDENT")
                .antMatchers(HttpMethod.PUT, "/lectures/*/students/*/add").hasRole("STUDENT")
                .antMatchers("/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("student").password("{noop}student").roles("STUDENT")
                .and()
                .withUser("admin").password("{noop}admin").roles("STUDENT", "ADMIN");
    }

}
