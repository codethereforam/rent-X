package priv.thinkam.rentx.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 注册控制器
 *
 * @author thinkam
 * @date 2019/02/16
 */
@Slf4j
@Controller
public class RegisterController {

	/**
	 * 跳转到“注册页面”
	 *
	 * @return page
	 */
	@GetMapping("/register")
	public String register() {
		return "register";
	}

}
