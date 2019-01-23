package priv.thinkam.rentx.web.dao.mapper;

import priv.thinkam.rentx.web.dao.dto.RoleResourceDTO;
import priv.thinkam.rentx.web.dao.entity.RoleResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 角色资源关系 Mapper
 *
 * @author yanganyu
 * @date 2019-01-23
 */
public interface RoleResourceMapper extends BaseMapper<RoleResource> {
	/**
	 * query all RoleResourceDTO
	 *
	 * @author yanganyu
	 * @date 1/23/19 11:31 PM
	 * @return RoleResourceDTO list
	 */
	List<RoleResourceDTO> listRoleResourceDTO();
}
