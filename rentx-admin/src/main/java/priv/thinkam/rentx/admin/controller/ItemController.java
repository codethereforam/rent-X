package priv.thinkam.rentx.admin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import priv.thinkam.rentx.common.base.BaseController;
import priv.thinkam.rentx.common.base.Response;
import priv.thinkam.rentx.web.api.CategoryServiceApi;
import priv.thinkam.rentx.web.api.ItemServiceApi;
import priv.thinkam.rentx.web.api.dto.CategorySelectApiDTO;
import priv.thinkam.rentx.web.api.param.ItemDailyStatsApiParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 租用项后台管理
 *
 * @author thinkam
 * @date 2019/02/15
 */
@Slf4j
@Controller
@RequestMapping("/items")
public class ItemController extends BaseController {
	@Reference(version = "${rentx.web.service.version}", url = "${rentx.web.service.url}")
	private ItemServiceApi itemServiceApi;
	@Reference(version = "${rentx.web.service.version}", url = "${rentx.web.service.url}")
	private CategoryServiceApi categoryServiceApi;

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
	public Response patchStatus(@PathVariable Integer id,@PathVariable Integer status) {
		log.info("patch item status, id={}, status={}", id, status);
		// 获取当前用户ID
		final int userId = -1;
		return itemServiceApi.patchStatus(id, status,userId);
	}

	@GetMapping("/daily-stats")
	public String dailyStatsPage(Model model) {
		List<CategorySelectApiDTO> categorySelectApiDTOList =categoryServiceApi.listCategorySelectApiDTO();
		Map<Integer, String> categorySelectIdNameMap = new HashMap<>();
		for (CategorySelectApiDTO categorySelectApiDTO : categorySelectApiDTOList) {
			categorySelectIdNameMap.put(categorySelectApiDTO.getId(), categorySelectApiDTO.getName());
		}
		model.addAttribute("categorySelectIdNameMap", categorySelectIdNameMap);
		model.addAttribute("statusSelectList", itemServiceApi.listItemStatusSelectApiDTO());
		return "item_daily_stats";
	}

	@PostMapping("/daily-stats")
	public String dailyStats(ItemDailyStatsApiParam itemDailyStatsApiParam, RedirectAttributes redirectAttributes) {
		itemDailyStatsApiParam.setBeginAddDate(StringUtils.trimToNull(itemDailyStatsApiParam.getBeginAddDate()));
		itemDailyStatsApiParam.setEndAddDate(StringUtils.trimToNull(itemDailyStatsApiParam.getEndAddDate()));
		redirectAttributes.addFlashAttribute("dailyStatsList", itemServiceApi.listItemDailyStatsApiDTO(itemDailyStatsApiParam));
		redirectAttributes.addFlashAttribute("itemDailyStatsApiParam", itemDailyStatsApiParam);
		return redirect("daily-stats");
	}
}
