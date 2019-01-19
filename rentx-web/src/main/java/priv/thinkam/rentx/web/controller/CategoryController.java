package priv.thinkam.rentx.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.thinkam.rentx.common.base.BaseController;
import priv.thinkam.rentx.common.base.Response;
import priv.thinkam.rentx.service.CategoryService;

import javax.annotation.Resource;

/**
 * 类别 controller
 *
 * @author yanganyu
 * @date 2019-01-19
 */
@Slf4j
@Controller
@RequestMapping("/categories")
public class CategoryController extends BaseController {
	@Resource
	private CategoryService categoryService;

	@ResponseBody
	@GetMapping
	public Response list() {
		return new Response()
				.setCode(Response.CodeEnum.SUCCESS)
				.setData(categoryService.list());
	}
}
