package priv.thinkam.rentx.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import priv.thinkam.rentx.common.base.Constant;
import priv.thinkam.rentx.web.common.base.WebConstant;
import priv.thinkam.rentx.web.dao.dto.RoleResourceDTO;
import priv.thinkam.rentx.web.dao.dto.UserRoleDTO;
import priv.thinkam.rentx.web.dao.mapper.UserRoleMapper;
import priv.thinkam.rentx.web.filter.CaptchaValidationFilter;
import priv.thinkam.rentx.web.service.RoleResourceService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * SpringSecurity config
 *
 * @author yanganyu
 * @date 2019/1/23 14:55
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Resource
	private UserRoleMapper userRoleMapper;
	@Resource
	private RoleResourceService roleResourceService;
	@Value("${rentx.security.static-asset-path}")
	private String[] staticAssetPath;
	@Resource
	private CaptchaValidationFilter captchaValidationFilter;

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers(staticAssetPath)
				.antMatchers("/chatServer/**")
				.regexMatchers(HttpMethod.GET, "/register", "/forget", "/captcha", "/captcha/error")
				.regexMatchers(HttpMethod.POST, "/users", "/emails/(.+)/send-captcha", "/users/(.+)/check-exists");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		http.csrf().disable();
		// 在用户名密码认证过滤器钱添加验证码校验过滤器
		http.addFilterBefore(captchaValidationFilter, UsernamePasswordAuthenticationFilter.class);
		List<RoleResourceDTO> roleResourceDTOList = roleResourceService.listRoleResourceDTOPlusRoot();
		log.info("RoleResourceDTO list: {}", roleResourceDTOList);
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
		for (RoleResourceDTO dto : roleResourceDTOList) {
			registry.regexMatchers(HttpMethod.resolve(dto.getResourceMethod()), dto.getResourceURL())
					.hasAnyRole(dto.getRoleIdentifierConcat().split(Constant.Separator.COMMA));
		}
		registry.anyRequest().hasRole(WebConstant.RoleIdentifier.ROOT)
				.and().formLogin().loginPage("/login").permitAll()
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login")
				.deleteCookies("JSESSIONID").invalidateHttpSession(true).permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(inMemoryUserDetailsManager());
	}

	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		List<UserRoleDTO> userRoleDTOList = userRoleMapper.listUserRoleDTO();
		log.info("userRoleDTO list: {}", userRoleDTOList.toString());
		List<UserDetails> userDetailsList = new ArrayList<>();
		for (UserRoleDTO dto : userRoleDTOList) {
			userDetailsList.add(
					User.withUsername(dto.getUsername())
					.password(dto.getPassword())
					.roles(dto.getRoleIdentifier()).build()
			);
		}
		return new InMemoryUserDetailsManager(userDetailsList);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}