package priv.thinkam.rentx.web.service.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import priv.thinkam.rentx.common.enums.RoleEnum;

/**
 * 认证授权工具
 *
 * @author yanganyu
 * @date 2019/3/17 14:29
 */
public class AuthUtil {
	/**
	 * 登出URL
	 */
	public static final String LOGOUT_URL = "/logout";

	/**
	 * 是否拥有ROOT角色
	 *
	 * @param authentication authentication
	 * @return true: has root role
	 * @author yanganyu
	 * @date 2019/3/17 14:27
	 */
	public static boolean hasRootRole(Authentication authentication) {
		for (GrantedAuthority authority : authentication.getAuthorities()) {
			if (RoleEnum.ROOT.getName().equals(authority.getAuthority().substring(5))) {
				return true;
			}
		}
		return false;
	}

}
