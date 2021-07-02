package APIFullstack.websachcu.Config;

import APIFullstack.websachcu.Service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    SecurityService securityService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityService).passwordEncoder(passwordEncoder());
    }
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET,"/","/homePage","/login","/logout").permitAll();
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET,"/bookPage","/userPage")
                .access("hasAnyRole('ROLE_ADMIN','ROLE_USER')");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET,"/admin")
                .access("hasRole('ROLE_ADMIN')");
        httpSecurity.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
        httpSecurity.authorizeRequests().and().formLogin()
                .loginProcessingUrl("/login_backend")
                .loginPage("/login")
                .defaultSuccessUrl("/homePage")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")

                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");

    }
}
