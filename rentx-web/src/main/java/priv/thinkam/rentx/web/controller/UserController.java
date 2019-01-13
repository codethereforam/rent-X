package priv.thinkam.rentx.web.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@Slf4j
@Controller
@RequestMapping("/users")
public class UserController extends BaseController {
	@Resource
	private UserService userService;

	@GetMapping
	@ResponseBody
	public List<User> list() {
		log.info("UserController list called...");
		return userService.list();
	}

	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("userList", userService.list());
		return "index";
	}

	@GetMapping("/count")
	@ResponseBody
	public Integer count() {
		return userService.testCount();
	}

	@GetMapping("/{id}")
	@ResponseBody
	public User get(@PathVariable Integer id) {
		return userService.getById(id);
	}
}
