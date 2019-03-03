package priv.thinkam.rentx.web.service.validator.auth;

/**
 * 认证校验分组
 *
 * @author yanganyu
 * @date 2019/3/3 11:15
 */
public class AuthValidatorGroup {
	/**
	 * 注册
	 *
	 * @author yanganyu
	 * @date 2019/3/3 11:16
	 */
	public interface Register {
	}

	/**
	 * 发送邮件验证码
	 *
	 * @author yanganyu
	 * @date 2019/3/3 11:16
	 */
	public interface SendEmailCaptcha {
	}

	/**
	 * 重置密码
	 *
	 * @author yanganyu
	 * @date 2019/3/3 11:16
	 */
	public interface ResetPassword {
	}

}
