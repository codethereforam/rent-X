package priv.thinkam.rentx.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.thinkam.rentx.common.base.BaseController;
import priv.thinkam.rentx.web.dao.entity.Tuser;
import priv.thinkam.rentx.web.service.TuserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 测试用户表 controller
 *
 * @author yanganyu
 * @date 2019-01-19
 */
@Slf4j
@Controller
@RequestMapping("/tusers")
public class TuserController extends BaseController {
	@Resource
	private TuserService tuserService;

	@GetMapping
	@ResponseBody
	public List<Tuser> list() {
		log.info("UserController list called...");
		return tuserService.list();
	}

	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("userList", tuserService.list());
		model.addAttribute("userCount", tuserService.count());
		return "index";
	}

	@GetMapping("/count")
	@ResponseBody
	public Integer count() {
		return tuserService.testCount();
	}

	@GetMapping("/{id}")
	@ResponseBody
	public Tuser get(@PathVariable Integer id) {
		return tuserService.getById(id);
	}
}
