package priv.thinkam.rentx.common.base;

/**
 * 常量类
 *
 * @author thinkam
 * @date 2019/01/13
 */
public interface Constant {

	/**
	 * 分隔符
	 *
	 * @author yanganyu
	 * @date 1/25/19 10:43 PM
	 */
	interface Separator {
		/**
		 * comma: ","
		 */
		String COMMA = ",";
		/**
		 * minus: "-"
		 */
		String MINUS = "-";
	}

	/**
	 * 错误信息
	 *
	 * @author yanganyu
	 * @date 1/27/19 12:23 AM
	 */
	interface ErrorMsg {
		String SYSTEM_INTERNAL_ERROR = "系统内部错误";
	}

	/**
	 * 图片验证码
	 *
	 * @author yanganyu
	 * @date 2019/3/3 21:43
	 */
	interface Captcha {
		String UUID_HEADER = "uuid";
	}

	/**
	 * session
	 *
	 * @author yanganyu
	 * @date 2019/3/3 21:44
	 */
	interface EmailCaptcha {
		/**
		 * email captcha redis key prefix
		 */
		String REDIS_KEY_PREFIX = "email_captcha-";
	}
}
