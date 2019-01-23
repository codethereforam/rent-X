package priv.thinkam.rentx.web.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import priv.thinkam.rentx.web.dao.dto.UserRoleDTO;
import priv.thinkam.rentx.web.dao.entity.UserRole;

import java.util.List;

/**
 * 用户角色关系 Mapper
 *
 * @author yanganyu
 * @date 2019-01-23
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {
	/**
	 * query all UserRoleDTO
	 *
	 * @return UserRoleDTO list
	 * @author yanganyu
	 * @date 1/23/19 9:47 PM
	 */
	List<UserRoleDTO> listUserRoleDTO();
}
