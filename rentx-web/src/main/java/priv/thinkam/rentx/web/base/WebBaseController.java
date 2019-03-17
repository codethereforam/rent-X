package priv.thinkam.rentx.web.base;

import org.springframework.security.core.Authentication;
import priv.thinkam.rentx.common.base.BaseController;
import priv.thinkam.rentx.common.exception.ResourceForbiddenException;
import priv.thinkam.rentx.web.common.base.WebConstant;
import priv.thinkam.rentx.web.service.util.AuthUtil;

import javax.servlet.http.HttpSession;

/**
 * Web层基础控制器
 *
 * @author yanganyu
 * @date 2019/3/17 14:46
 */
public class WebBaseController extends BaseController {

	/**
	 * 获取当前用户ID，如果时ROOT用户，返回null
	 *
	 * @param authentication authentication
	 * @param session        session
	 * @return userId
	 * @author yanganyu
	 * @date 2019/3/17 14:47
	 */
	protected Integer currentNonRootUserId(Authentication authentication, HttpSession session) {
		Integer userId = null;
		if (!AuthUtil.hasRootRole(authentication)) {
			userId = (Integer) session.getAttribute(WebConstant.Session.CURRENT_USER_ID_SESSION_KEY);
			if (userId == null) {
				throw new ResourceForbiddenException("没有权限");
			}
		}
		return userId;
	}

	/**
	 * 当前用户ID
	 *
	 * @param session session
	 * @return java.lang.Integer
	 * @author yanganyu
	 * @date 2019/3/17 15:18
	 */
	protected Integer currentUserId(HttpSession session) {
		Integer userId = (Integer) session.getAttribute(WebConstant.Session.CURRENT_USER_ID_SESSION_KEY);
		if (userId == null) {
			throw new ResourceForbiddenException("没有权限");
		}
		return userId;
	}
}
