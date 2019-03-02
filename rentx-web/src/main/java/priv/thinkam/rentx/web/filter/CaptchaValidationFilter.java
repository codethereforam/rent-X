package priv.thinkam.rentx.web.filter;

import com.google.code.kaptcha.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import priv.thinkam.rentx.common.base.Constant;
import priv.thinkam.rentx.web.exception.CaptchaValidationException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码校验过滤器
 *
 * @author yanganyu
 * @date 2019/3/2 20:14
 */
@Slf4j
@Component
public class CaptchaValidationFilter extends OncePerRequestFilter {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	private static final String CAPTCHA_ERROR_MESSAGE = "验证码校验失败";
	private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler("/captcha/error");


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		if (StringUtils.equalsIgnoreCase("/login", request.getRequestURI())
				&& StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
			try {
				this.validateCaptcha(request);
			} catch (CaptchaValidationException e) {
				failureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
		}
		filterChain.doFilter(request, response);
	}

	private void validateCaptcha(HttpServletRequest request) {
		String uuidHeader = request.getParameter(Constant.Captcha.UUID_HEADER);
		if (StringUtils.isBlank(uuidHeader)) {
			throw new CaptchaValidationException(CAPTCHA_ERROR_MESSAGE);
		}
		String inputCaptcha = request.getParameter("captcha");
		if (StringUtils.isBlank(inputCaptcha)) {
			throw new CaptchaValidationException("验证码不能为空");
		}
		String redisKey = Constants.KAPTCHA_SESSION_KEY + Constant.Separator.MINUS + uuidHeader;
		String realCaptcha =
				stringRedisTemplate.opsForValue().get(redisKey);
		if (realCaptcha == null) {
			throw new CaptchaValidationException(CAPTCHA_ERROR_MESSAGE);
		}
		if (!realCaptcha.equalsIgnoreCase(inputCaptcha)) {
			throw new CaptchaValidationException("验证码不正确");
		}
		stringRedisTemplate.delete(redisKey);
	}
}
