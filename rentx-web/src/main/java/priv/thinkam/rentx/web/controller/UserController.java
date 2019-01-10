package priv.thinkam.rentx.web.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.thinkam.rentx.common.base.BaseController;
import priv.thinkam.rentx.dao.entity.User;
import priv.thinkam.rentx.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户表 controller
 *
 * @author yanganyu
 * @date 2019-01-10
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	@Resource
	private UserService userService;

	@GetMapping("/list")
	@ResponseBody
	public List<User> list() {
		return userService.list();
	}

	@GetMapping("/count")
	@ResponseBody
	public Integer count() {
		return userService.testCount();
	}

	@GetMapping("/get")
	@ResponseBody
	public User get() {
		return userService.getById(1);
	}
}
