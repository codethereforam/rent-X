package priv.thinkam.rentx.web.user.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.thinkam.rentx.common.base.BaseController;
import priv.thinkam.rentx.dao.entity.User;
import priv.thinkam.rentx.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 系统表 前端控制器
 * </p>
 *
 * @author yanganyu
 * @since 2019-01-10
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
	@Resource
	private UserService userService;

	@GetMapping("/list")
	public List<User> list() {
		return userService.list();
	}

	@GetMapping("/count")
	public Integer count() {
		return userService.testCount();
	}
}
