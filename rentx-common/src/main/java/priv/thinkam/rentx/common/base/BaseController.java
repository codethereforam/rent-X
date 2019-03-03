package priv.thinkam.rentx.common.base;

/**
 * base controller
 *
 * @author yanganyu
 * @date 2019/1/8 17:09
 */
public class BaseController {

	/**
	 * redirect to url
	 *
	 * @param url url
	 * @return "forward:" + url
	 */
	protected String redirect(String url) {
		return "redirect:" + url;
	}

	/**
	 * forward to url
	 *
	 * @param url url
	 * @return "forward:" + url
	 */
	protected String forward(String url) {
		return "forward:" + url;
	}

}
