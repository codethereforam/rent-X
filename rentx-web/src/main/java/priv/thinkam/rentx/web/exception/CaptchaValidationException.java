package priv.thinkam.rentx.web.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码校验异常
 *
 * @author yanganyu
 * @date 2019/3/2 17:07
 */
public class CaptchaValidationException extends AuthenticationException {

	public CaptchaValidationException(String msg) {
		super(msg);
	}
}
