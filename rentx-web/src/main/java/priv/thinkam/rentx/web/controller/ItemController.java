package priv.thinkam.rentx.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import priv.thinkam.rentx.common.base.BaseController;
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
	 * @return page
	 */
	@GetMapping("/in")
	public String startRentIn() {
		return "my_rent_in";
	}
}
