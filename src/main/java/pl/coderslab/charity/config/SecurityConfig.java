package pl.coderslab.charity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/resources/**", "/guest/**", "/test").permitAll()
//                .antMatchers("/test").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
//                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()//czy to porzebne?
                .and()
                .formLogin().loginPage("/guest/login").defaultSuccessUrl("/router")//nie wiem czemu router nie chodzi po gueście
                .and()
                .logout().logoutSuccessUrl("/");
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
