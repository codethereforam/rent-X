package priv.thinkam.rentx.web.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import priv.thinkam.rentx.common.enums.RoleEnum;
import priv.thinkam.rentx.web.common.base.WebConstant;
import priv.thinkam.rentx.web.service.MenuService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 认证成功处理器
 *
 * @author yanganyu
 * @date 2019/3/16 17:08
 */
@Component
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	@Autowired
	private MenuService menuService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		Set<Integer> roleIdSet = new HashSet<>();
		for (GrantedAuthority authority : authentication.getAuthorities()) {
			roleIdSet.add(RoleEnum.getByName(authority.getAuthority().substring(5)).getId());
		}
		// 将用户菜单保存到session
		request.getSession().setAttribute(WebConstant.Session.MENU_VO_LIST_SESSION_KEY, menuService.listMenuVO(roleIdSet));
		response.sendRedirect("/");
	}

}
