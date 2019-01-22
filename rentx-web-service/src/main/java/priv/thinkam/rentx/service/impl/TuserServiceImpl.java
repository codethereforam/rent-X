package priv.thinkam.rentx.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import priv.thinkam.rentx.api.TuserServiceApi;
import priv.thinkam.rentx.api.bo.TuserBo;
import priv.thinkam.rentx.api.query.TuserQuery;
import priv.thinkam.rentx.dao.entity.Tuser;
import priv.thinkam.rentx.service.TuserService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author thinkam
 * @date 2019/01/21
 */
@Service(version = "${rentx.web.service.version}")
public class TuserServiceImpl implements TuserServiceApi {
	@Resource
	private TuserService tuserService;

	@Override
	public List<TuserBo> list(TuserQuery tuserQuery) {
		return tuserService.list(new QueryWrapper<Tuser>().lambda().likeRight(Tuser::getName, tuserQuery.nameLike)).stream()
				.map(tuser -> new TuserBo().setName(tuser.getName()).setAge(tuser.getAge()))
				.collect(Collectors.toList());
	}
}
