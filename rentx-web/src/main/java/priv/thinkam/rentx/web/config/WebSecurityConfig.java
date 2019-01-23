package priv.thinkam.rentx.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import priv.thinkam.rentx.web.dao.dto.RoleResourceDTO;
import priv.thinkam.rentx.web.dao.dto.UserRoleDTO;
import priv.thinkam.rentx.web.dao.mapper.RoleResourceMapper;
import priv.thinkam.rentx.web.dao.mapper.UserRoleMapper;

import javax.annotation.Resource;
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
	private RoleResourceMapper roleResourceMapper;

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
		List<RoleResourceDTO> roleResourceDTOList = roleResourceMapper.listRoleResourceDTO();
		log.info("RoleResourceDTO list: {}", roleResourceDTOList);
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
		for (RoleResourceDTO dto : roleResourceDTOList) {
			registry.regexMatchers(HttpMethod.resolve(dto.getResourceMethod()), dto.getResourceURL()).hasRole(dto.getRoleIdentifier());
		}
		registry.and().formLogin().loginPage("/login").permitAll()
				.and().logout().permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		List<UserRoleDTO> userRoleDTOList = userRoleMapper.listUserRoleDTO();
		log.info("userRoleDTO list: {}", userRoleDTOList.toString());
		for (UserRoleDTO dto : userRoleDTOList) {
			auth.inMemoryAuthentication().withUser(dto.getUsername()).password(dto.getPassword()).roles(dto.getRoleIdentifier());
		}
	}
}