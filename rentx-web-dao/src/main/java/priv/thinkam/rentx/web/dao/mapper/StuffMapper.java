package priv.thinkam.rentx.web.dao.mapper;

import priv.thinkam.rentx.web.dao.dto.StuffDTO;
import priv.thinkam.rentx.web.dao.entity.Stuff;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 物品 Mapper
 *
 * @author yanganyu
 * @date 2019-01-19
 */
public interface StuffMapper extends BaseMapper<Stuff> {

	List<StuffDTO> listStuffDTO();

	List<StuffDTO> listOutStuffDTO(Integer userId);
}
