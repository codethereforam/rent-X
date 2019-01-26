package priv.thinkam.rentx.admin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.thinkam.rentx.common.base.BaseController;
import priv.thinkam.rentx.common.base.Response;
import priv.thinkam.rentx.web.api.CategoryServiceApi;
import priv.thinkam.rentx.web.api.bo.CategoryBO;

import java.util.List;

/**
 * 类别管理 controller
 *
 * @author thinkam
 * @date 2019/01/21
 */
@Slf4j
@Controller
@RequestMapping("/categories")
public class CategoryController extends BaseController {
	@Reference(version = "${rentx.web.service.version}", url = "${rentx.web.service.url}")
	private CategoryServiceApi categoryService;

	@GetMapping("/index")
	public String index() {
		return "category_index";
	}

	@GetMapping
	@ResponseBody
	public Response list() {
		return new Response<List<CategoryBO>>()
				.setCode(Response.CodeEnum.SUCCESS)
				.setData(categoryService.listCategoryBO());
	}
}
