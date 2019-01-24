package priv.thinkam.rentx.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import priv.thinkam.rentx.web.dao.dto.RoleResourceDTO;
import priv.thinkam.rentx.web.dao.entity.Resource;
import priv.thinkam.rentx.web.dao.entity.RoleResource;
import priv.thinkam.rentx.web.dao.mapper.RoleResourceMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色资源关系 service
 *
 * @author yanganyu
 * @date 2019-01-23
 */
@Slf4j
@Service
public class RoleResourceService extends ServiceImpl<RoleResourceMapper, RoleResource> implements IService<RoleResource> {
	@javax.annotation.Resource
	private RoleResourceMapper roleResourceMapper;
	@javax.annotation.Resource
	private ResourceService resourceService;

	/**
	 * 查询所有资源角色关系，并给所有资源添加和ROOT角色的关系
	 *
	 * @author yanganyu
	 * @date 1/24/19 11:03 PM
	 * @return RoleResourceDTO list
	 */
	public List<RoleResourceDTO> listRoleResourceDTOPlusRoot() {
		List<RoleResourceDTO> result = roleResourceMapper.listRoleResourceDTO();
		List<RoleResourceDTO> rootResourceDTOList =
				resourceService.list(new QueryWrapper<Resource>().lambda().eq(Resource::getMark, 1)).stream()
				.map(r -> new RoleResourceDTO()
						.setResourceURL(r.getUrl())
						.setResourceMethod(r.getMethod())
						.setRoleIdentifier("ROOT"))
				.collect(Collectors.toList());
		result.addAll(rootResourceDTOList);
		return result;
	}

}
