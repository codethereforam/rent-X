package priv.thinkam.rentx.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.thinkam.rentx.admin.service.UserService;
import priv.thinkam.rentx.common.base.BaseController;
import priv.thinkam.rentx.common.base.Response;

import javax.annotation.Resource;

/**
 * 用户表 controller
 *
 * @author yanganyu
 * @date 2019-01-25
 */
@Slf4j
@Controller
@RequestMapping("/users")
public class UserController extends BaseController {
	@Resource
	private UserService userService;

	@GetMapping
	@ResponseBody
	public Response list() {
		return Response.success(userService.listUsers());
	}

}
