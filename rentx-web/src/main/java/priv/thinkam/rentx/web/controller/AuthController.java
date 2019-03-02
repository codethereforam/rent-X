package priv.thinkam.rentx.web.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import priv.thinkam.rentx.common.base.Constant;
import priv.thinkam.rentx.web.exception.CaptchaValidationException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 身份认证控制器
 *
 * @author yanganyu
 * @date 2019/1/23 16:21
 */
@Slf4j
@Controller
public class AuthController {
	@Resource
	private Producer kaptchaProducer;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 跳转到”登录“页面
	 *
	 * @return page
	 */
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	/**
	 * 跳转到”忘记密码“页面
	 *
	 * @return page
	 */
	@GetMapping("/forget")
	public String forgetPassword() {
		return "reset";
	}

	/**
	 * 获取验证码（base64字符串）
	 *
	 * @param response response
	 * @author yanganyu
	 * @date 2019/3/2 22:55
	 */
	@GetMapping("/captcha")
	public void getCaptcha(HttpServletResponse response) {
		String uuid = UUID.randomUUID().toString();
		response.setHeader(Constant.Captcha.UUID_HEADER, uuid);
		// create the text for the image
		String kaptchaProducerText = kaptchaProducer.createText();
		// store the text in the session
		log.debug("generate captcha: {}", kaptchaProducerText);
		String redisKey = Constants.KAPTCHA_SESSION_KEY + Constant.Separator.MINUS + uuid;
		stringRedisTemplate.opsForValue().set(redisKey, kaptchaProducerText);
		stringRedisTemplate.expire(redisKey, 5, TimeUnit.MINUTES);
		// create the image with the text
		BufferedImage bufferedImage = kaptchaProducer.createImage(kaptchaProducerText);
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			 PrintWriter printWrite = response.getWriter()
		) {
			ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
			byte[] imageInByte = byteArrayOutputStream.toByteArray();
			byteArrayOutputStream.flush();
			String encodedString = Base64.getEncoder().encodeToString(imageInByte);
			printWrite.write(encodedString);
			printWrite.flush();
		} catch (IOException e) {
			log.error("验证码生成错误", e);
		}
	}

	/**
	 * 验证码校验失败
	 *
	 * @param session session
	 * @return page
	 * @author yanganyu
	 * @date 2019/3/3 1:25
	 */
	@RequestMapping("/captcha/error")
	public String captchaError(HttpSession session, Model model) {
		//spring security默认会把异常存到session中。
		AuthenticationException authenticationException =
				(AuthenticationException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		//判断异常是否是我们自定义的验证码认证异常
		if (authenticationException instanceof CaptchaValidationException) {
			//验证码认证错误标识，存入request中只针对本次请求。不影响整个会话
			model.addAttribute("captchaErrorMsg", authenticationException.getMessage());
		}
		return "login";
	}
}
