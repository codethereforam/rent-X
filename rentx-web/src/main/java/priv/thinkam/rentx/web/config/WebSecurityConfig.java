package priv.thinkam.rentx.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
import priv.thinkam.rentx.web.dao.mapper.UserRoleMapper;
import priv.thinkam.rentx.web.service.RoleResourceService;

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
	private RoleResourceService roleResourceService;
	@Value("${spring.security.ignore.paths}")
	private String[] ignorePath;

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers(ignorePath);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		List<RoleResourceDTO> roleResourceDTOList = roleResourceService.listRoleResourceDTOPlusRoot();
		log.info("RoleResourceDTO list: {}", roleResourceDTOList);
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
		for (RoleResourceDTO dto : roleResourceDTOList) {
			registry.regexMatchers(HttpMethod.resolve(dto.getResourceMethod()), dto.getResourceURL()).hasRole(dto.getRoleIdentifier());
		}
		registry.anyRequest().hasRole("ROOT")
				.and().formLogin().loginPage("/login").permitAll()
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