package priv.thinkam.rentx.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import priv.thinkam.rentx.common.base.BaseController;
import priv.thinkam.rentx.web.service.StuffService;

import javax.annotation.Resource;

/**
 * 物品 controller
 *
 * @author yanganyu
 * @date 2019-01-19
 */
@Slf4j
@Controller
@RequestMapping("/stuffs")
public class StuffController extends BaseController {
	@Resource
	private StuffService stuffService;

	/**
	 * 开始租用
	 *
	 * <pre>
	 *     "不出租"的不显示
	 *     "已租"的显示应还日期
	 *     "未租"的显示租用操作
	 * </pre>
	 *
	 * @return page
	 */
	@GetMapping("/in")
	public String startRentIn(Model model) {
		model.addAttribute("stuffInVOList", stuffService.listStuffInVO());
		return "start_rent_in";
	}

	/**
	 * 开始出租
	 *
	 * @return page
	 */
	@GetMapping("/out/start")
	public String startRentOut() {
		return "start_rent_out";
	}

	/**
	 * 我的出租
	 *
	 * @return page
	 */
	@GetMapping("/out")
	public String myRentOut() {
		return "my_rent_out";
	}
}
