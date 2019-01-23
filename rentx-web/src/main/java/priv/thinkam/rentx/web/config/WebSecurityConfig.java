package priv.thinkam.rentx.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * SpringSecurity config
 *
 * @author yanganyu
 * @date 2019/1/23 14:55
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) {
		web.ignoring()
				.antMatchers("/plugins/**")
				.antMatchers("/css/**")
				.antMatchers("/js/**")
				.antMatchers("/favicon.ico");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/tusers*/**").hasRole("role1")
				.antMatchers("/categories*/**").hasRole("role2")
				.and()
				.formLogin().loginPage("/login").permitAll()
				.and()
				.logout().permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("u1").password("{noop}p1").roles("role1").and()
				.withUser("u2").password("{noop}p2").roles("role2");
	}
}