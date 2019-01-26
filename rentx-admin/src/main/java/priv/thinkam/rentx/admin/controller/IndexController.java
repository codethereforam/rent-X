package priv.thinkam.rentx.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 基础控制器
 *
 * @author yanganyu
 * @date 2018/10/10 15:17
 */
@Slf4j
@Controller
@RequestMapping
public class IndexController {

	/**
	 * 首页
	 *
	 * @return java.lang.String
	 * @author yanganyu
	 * @date 2018/10/11 9:24
	 */
	@GetMapping
	public String index() {
		return "index";
	}

}