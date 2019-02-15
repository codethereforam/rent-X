package priv.thinkam.rentx.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import priv.thinkam.rentx.common.base.BaseController;
import priv.thinkam.rentx.common.base.Response;
import priv.thinkam.rentx.web.service.ItemService;

import javax.annotation.Resource;

/**
 * 出租项 controller
 *
 * @author yanganyu
 * @date 2019-01-19
 */
@Slf4j
@Controller
@RequestMapping("/items")
public class ItemController extends BaseController {
	@Resource
	private ItemService itemService;

	/**
	 * 我的租用
	 *
	 * <pre>
	 *     "申请中"的显示"取消申请"操作
	 * </pre>
	 *
	 * @return page
	 */
	@GetMapping("/in")
	public String startRentIn(Model model) {
		// 获取当前用户ID
		int userId = 3;
		model.addAttribute("personalItemVOList", itemService.listPersonItemVO(userId));
		return "my_rent_in";
	}

	/**
	 * 取消申请
	 *
	 * @param id item id
	 * @return Response
	 */
	@ResponseBody
	@PostMapping("/{id}/cancel-apply")
	public Response cancelApply(@PathVariable Integer id) {
		// 获取当前用户ID
		final int userId = 3;
		return itemService.cancelApply(id, userId);
	}
}
