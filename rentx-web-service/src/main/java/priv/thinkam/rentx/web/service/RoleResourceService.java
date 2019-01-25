package priv.thinkam.rentx.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import priv.thinkam.rentx.web.dao.dto.RoleResourceDTO;
import priv.thinkam.rentx.web.dao.entity.RoleResource;
import priv.thinkam.rentx.web.dao.mapper.RoleResourceMapper;

import java.util.List;

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

	/**
	 * 查询所有资源角色关系，并给所有资源添加和ROOT角色的关系
	 *
	 * @author yanganyu
	 * @date 1/24/19 11:03 PM
	 * @return RoleResourceDTO list
	 */
	public List<RoleResourceDTO> listRoleResourceDTOPlusRoot() {
		List<RoleResourceDTO> roleResourceDTOList = roleResourceMapper.listRoleResourceDTO();
		for (RoleResourceDTO roleResourceDTO : roleResourceDTOList) {
			if(StringUtils.isNotBlank(roleResourceDTO.getRoleIdentifierConcat())) {
				roleResourceDTO.setRoleIdentifierConcat(roleResourceDTO.getRoleIdentifierConcat() + ",ROOT");
			}
		}
		return roleResourceDTOList;
	}

}
