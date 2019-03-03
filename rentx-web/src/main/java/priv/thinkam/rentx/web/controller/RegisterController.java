package priv.thinkam.rentx.web.controller;

import com.baidu.unbiz.fluentvalidator.*;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.google.common.collect.Iterables;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import priv.thinkam.rentx.common.base.BaseController;
import priv.thinkam.rentx.common.base.Constant;
import priv.thinkam.rentx.common.base.Response;
import priv.thinkam.rentx.common.util.StringUtil;
import priv.thinkam.rentx.web.dao.entity.User;
import priv.thinkam.rentx.web.service.MailService;
import priv.thinkam.rentx.web.service.UserService;
import priv.thinkam.rentx.web.service.param.RegisterParam;
import priv.thinkam.rentx.web.service.validator.auth.AuthValidatorGroup;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import static com.baidu.unbiz.fluentvalidator.ResultCollectors.toComplex;
import static com.baidu.unbiz.fluentvalidator.ResultCollectors.toSimple;

/**
 * 注册控制器
 *
 * @author thinkam
 * @date 2019/02/16
 */
@Slf4j
@Controller
public class RegisterController extends BaseController {
	@Resource
	private javax.validation.Validator hibernateValidator;
	@Resource
	private UserService userService;
	@Resource
	private MailService mailService;
	@Autowired
	private TemplateEngine templateEngine;
	@Autowired
	private InMemoryUserDetailsManager inMemoryUserDetailsManager;

	/**
	 * 跳转到“注册页面”
	 *
	 * @return page
	 */
	@GetMapping("/register")
	public String registerPage() {
		return "register";
	}

	/**
	 * 提交注册请求
	 *
	 * @param registerParam register Param
	 * @param session       session
	 * @return response
	 */
	@PostMapping("/users")
	@ResponseBody
	public Response register(@RequestBody RegisterParam registerParam, HttpSession session) {
		log.info("get registerParam: {}", registerParam);
		//数据验证,(返回结果)
		ComplexResult result = FluentValidator.checkAll(AuthValidatorGroup.Register.class)
				.failFast()
				.on(registerParam.getEmailCaptcha(), new ValidatorHandler<String>() {
					@Override
					public boolean validate(ValidatorContext context, String inputEmailCaptcha) {
						String errorMsg = "验证码错误，请检查邮箱地址或点击重新发送";
						if (inputEmailCaptcha == null) {
							context.addError(ValidationError.create(errorMsg).setField("emailCaptcha"));
							return false;
						}
						Object emailCaptcha = session.getAttribute(Constant.Session.EMAIL_CAPTCHA_SESSION_KEY);
						if (emailCaptcha == null || !inputEmailCaptcha.equalsIgnoreCase((String) emailCaptcha)) {
							context.addError(ValidationError.create(errorMsg).setField("emailCaptcha"));
							return false;
						}
						return true;
					}
				})
				.on(registerParam,
						new HibernateSupportedValidator<RegisterParam>().setHiberanteValidator(hibernateValidator))
				.on(registerParam.getUsername(), new ValidatorHandler<String>() {
					@Override
					public boolean validate(ValidatorContext context, String username) {
						if (inMemoryUserDetailsManager.userExists(username)) {
							context.addError(ValidationError.create("该用户名已有人使用").setField("username"));
							return false;
						}
						return true;
					}
				})
				.on(registerParam.getEmail(), new ValidatorHandler<String>() {
					@Override
					public boolean validate(ValidatorContext context, String email) {
						if (userService.existsEmail(email)) {
							context.addError(ValidationError.create("该邮箱已有人使用").setField("email"));
							return false;
						}
						return true;
					}
				})
				.doValidate()
				.result(toComplex());
		if (!result.isSuccess()) {
			ValidationError validationError = Iterables.getFirst(result.getErrors(), null);
			if (validationError == null) {
				return Response.FAIL;
			} else {
				return Response.fail(validationError.getErrorMsg(), validationError.getField());
			}
		}
		if (!registerParam.getPassword().equals(registerParam.getConfirmedPassword())) {
			return Response.fail("两个密码不匹配", "password");
		}
		// save user
		userService.save(getUserFrom(registerParam), registerParam.getRole());
		return Response.SUCCESS;
	}

	/**
	 * get user from RegisterParam
	 *
	 * @param registerParam registerParam
	 * @return User
	 */
	private User getUserFrom(RegisterParam registerParam) {
		User user = new User()
				.setUsername(registerParam.getUsername())
				.setPassword(new BCryptPasswordEncoder().encode(registerParam.getPassword()))
				.setEmail(registerParam.getEmail())
				.setSex(registerParam.getSex())
				.setStatus(true);
		user.completeAddParam(0);
		return user;
	}

	/**
	 * 发送邮件验证码
	 *
	 * @param email email
	 * @return Response
	 */
	@PostMapping("/emails/{email}/send-captcha")
	@ResponseBody
	public Response sendEmailCaptcha(@PathVariable String email, HttpSession session) {
		Result result = FluentValidator.checkAll(AuthValidatorGroup.SendEmailCaptcha.class)
				.failFast()
				.on(new RegisterParam().setEmail(email), new HibernateSupportedValidator<RegisterParam>().setHiberanteValidator(hibernateValidator))
				.doValidate()
				.result(toSimple());
		if (!result.isSuccess()) {
			return Response.fail(Iterables.getFirst(result.getErrors(), StringUtils.EMPTY));
		}
		if (userService.existsEmail(email)) {
			return Response.fail("该邮箱已有人使用");
		}
		String captcha = StringUtil.randomString(6);
		//保存到session
		session.setAttribute(Constant.Session.EMAIL_CAPTCHA_SESSION_KEY, captcha);
		Context context = new Context();
		context.setVariable("captcha", captcha);
		String emailContent = templateEngine.process("mail/email_captcha", context);
		mailService.sendEmailAsync(email, StringUtil.format("{}是您在rent-X的注册验证码", captcha), emailContent);
		return Response.SUCCESS;
	}

	/**
	 * 检查用户名是否已经存在
	 *
	 * @param username username
	 * @return response
	 */
	@PostMapping("/users/{username}/check-exists")
	@ResponseBody
	public Response checkUsernameExist(@PathVariable String username) {
		if (inMemoryUserDetailsManager.userExists(username)) {
			return Response.fail("该用户名已有人使用");
		}
		return Response.SUCCESS;
	}
}
