package priv.thinkam.rentx.admin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import priv.thinkam.rentx.common.base.Response;
import priv.thinkam.rentx.web.api.ItemServiceApi;

/**
 * 租用项后台管理
 *
 * @author thinkam
 * @date 2019/02/15
 */
@Slf4j
@Controller
@RequestMapping("/items")
public class ItemController {
	@Reference(version = "${rentx.web.service.version}", url = "${rentx.web.service.url}")
	private ItemServiceApi itemServiceApi;

	/**
	 * 跳转到租用项列表首页
	 *
	 * @return page
	 */
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("itemApiDTOList", itemServiceApi.listItemApiDTO());
		return "item_index";
	}

	@ResponseBody
	@PatchMapping("/{id}/status/{status}")
	public Response patchStatus(Integer id, Integer status) {
		log.info("patch item status, id={}, status={}", id, status);
		// 获取当前用户ID
		final int userId = 3;
		return itemServiceApi.patchStatus(id, status,userId);
	}
}
