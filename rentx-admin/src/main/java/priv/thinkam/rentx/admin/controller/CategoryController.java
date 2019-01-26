package priv.thinkam.rentx.admin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.thinkam.rentx.common.base.BaseController;
import priv.thinkam.rentx.common.base.Response;
import priv.thinkam.rentx.web.api.CategoryServiceApi;
import priv.thinkam.rentx.web.api.dto.CategoryApiDTO;
import priv.thinkam.rentx.web.api.param.CategoryApiParam;

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
	private CategoryServiceApi categoryServiceApi;

	/**
	 * 跳转到类别列表首页
	 *
	 * @return page
	 */
	@GetMapping("/index")
	public String index() {
		return "category_index";
	}

	/**
	 * 获取所有类别信息
	 *
	 * @return Response
	 */
	@GetMapping
	@ResponseBody
	public Response list() {
		return new Response<List<CategoryApiDTO>>()
				.setCode(Response.CodeEnum.SUCCESS)
				.setData(categoryServiceApi.listCategoryApiDTO());
	}

	/**
	 * 添加类别
	 *
	 * @param categoryApiParam categoryApiParam
	 * @return Response
	 */
	@PostMapping
	@ResponseBody
	public Response add(CategoryApiParam categoryApiParam) {
		log.info("categoryApiParam: {}", categoryApiParam);
		return categoryServiceApi.add(categoryApiParam);
	}
}
