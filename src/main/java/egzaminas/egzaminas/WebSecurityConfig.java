package egzaminas.egzaminas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	public MyUserDetailsService userDetailsService;
	@Autowired
	private LoginSuccessHandler loginSuccessHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/index.js", "/index.css", "/register").permitAll()
				.antMatchers(HttpMethod.POST, "/register").permitAll()
				.antMatchers("/categories/delete", "/movies/delete")
				.access("hasRole('ADMIN')")
				// https://stackoverflow.com/questions/7347183/using-spring-security-how-can-i-use-http-methods-e-g-get-put-post-to-disti
				.antMatchers(HttpMethod.POST, "/categories", "/movies")
				.access("hasRole('ADMIN')")
				.antMatchers(HttpMethod.POST, "/comments")
				.access("hasRole('USER')")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				// https://www.javadevjournal.com/spring/spring-security-success-handler/
				//.defaultSuccessUrl("/", true)
				.successHandler(loginSuccessHandler)
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
	    authProvider.setPasswordEncoder(encoder());
	    return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
	    auth.authenticationProvider(authProvider());
	}

	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
}