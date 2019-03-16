package priv.thinkam.rentx.web.dao.mapper;

import org.apache.ibatis.annotations.Param;
import priv.thinkam.rentx.web.dao.dto.MenuDTO;
import priv.thinkam.rentx.web.dao.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Set;

/**
 * 菜单 Mapper
 *
 * @author yanganyu
 * @date 2019-03-16
 */
public interface MenuMapper extends BaseMapper<Menu> {

	List<MenuDTO> listMenuDTOByRoleIdSet(@Param("roleIdSet") Set<Integer> roleIdSet);
}
