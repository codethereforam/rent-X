package priv.thinkam.rentx.admin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.thinkam.rentx.web.api.dto.TuserApiDTO;
import priv.thinkam.rentx.web.api.TuserServiceApi;
import priv.thinkam.rentx.web.api.query.TuserQuery;
import priv.thinkam.rentx.common.base.BaseController;
import priv.thinkam.rentx.common.base.Response;

import java.util.List;

/**
 * @author thinkam
 * @date 2019/01/21
 */
@Slf4j
@Controller
@RequestMapping("/tusers")
public class TuserController extends BaseController {
	@Reference(version = "${rentx.web.service.version}", url = "${rentx.web.service.url}")
	private TuserServiceApi tuserService;

	@GetMapping
	@ResponseBody
	public Response list() {
		return new Response<List<TuserApiDTO>>()
				.setCode(Response.CodeEnum.SUCCESS)
				.setData(tuserService.list(new TuserQuery().setNameLike("u")));
	}

}
