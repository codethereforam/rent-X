package priv.thinkam.rentx.web.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.thinkam.rentx.common.base.BaseController;

/**
 * test controller
 *
 * @author yanganyu
 * @date 2019/1/9 10:35
 */
@RestController
@RequestMapping("/test")
public class TestController extends BaseController {
	@GetMapping("/hello")
	public String hello() {
		return "hello world";
	}
}
