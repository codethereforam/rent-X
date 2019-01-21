package priv.thinkam.rentx.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import priv.thinkam.rentx.api.TuserResult;
import priv.thinkam.rentx.api.TuserServiceApi;
import priv.thinkam.rentx.service.TuserService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author thinkam
 * @date 2019/01/21
 */
@Service(
		version = "${rentx-web.service.version}",
		application = "${dubbo.application.id}",
		protocol = "${dubbo.protocol.id}",
		registry = "${dubbo.registry.id}"
)
public class TuserServiceImpl implements TuserServiceApi {
	@Resource
	private TuserService tuserService;

	@Override
	public List<TuserResult> list() {
		return tuserService.list().stream()
				.map(tuser -> new TuserResult(tuser.getName(), tuser.getAge()))
				.collect(Collectors.toList());
	}
}
