package priv.thinkam.rentx.web.common.base;

/**
 * web模块常量类
 *
 * @author thinkam
 * @date 2019/01/25
 */
public interface WebConstant {

	/**
	 * 角色标识符
	 *
	 * @author yanganyu
	 * @date 1/25/19 10:46 PM
	 */
	interface RoleIdentifier {
		/**
		 * ROOT角色标识符
		 */
		String ROOT = "ROOT";
	}

	interface Session {
		/**
		 * 登录成功保存的菜单session key
		 */
		String MENU_VO_LIST_SESSION_KEY = "session-menu-vo-list";
		String CURRENT_USER_ID_SESSION_KEY = "session-current-user-id";
	}
}
