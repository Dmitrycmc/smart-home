package io.github.dmitrycmc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    public void authConfig(AuthenticationManagerBuilder auth, PasswordEncoder encoder, UserDetailsService userDetailService) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("root")
                .password(encoder.encode("root"))
                .roles("SUPER_ADMIN");

        auth.userDetailsService(userDetailService);

    }

    @Configuration
    public static class UiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/**/*.css", "/**/*.js").permitAll()
                    .antMatchers("/**").authenticated()
                    .and()
                    .formLogin()
                    //.loginPage("/login")
                    .defaultSuccessUrl("/user", true);
        }
    }
}
