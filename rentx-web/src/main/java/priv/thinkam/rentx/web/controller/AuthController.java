package priv.thinkam.rentx.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 身份认证控制器
 *
 * @author yanganyu
 * @date 2019/1/23 16:21
 */
@Slf4j
@Controller
public class AuthController {

	/**
	 * 跳转到”登录“页面
	 *
	 * @return page
	 */
	@GetMapping("/login")
	public String login(){
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
}
