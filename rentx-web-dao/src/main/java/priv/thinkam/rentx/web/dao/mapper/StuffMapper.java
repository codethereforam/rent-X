package priv.thinkam.rentx.web.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import priv.thinkam.rentx.web.dao.dto.StuffDTO;
import priv.thinkam.rentx.web.dao.entity.Stuff;

import java.util.List;

/**
 * 物品 Mapper
 *
 * @author yanganyu
 * @date 2019-01-19
 */
public interface StuffMapper extends BaseMapper<Stuff> {

	List<StuffDTO> listStuffDTO();

	List<StuffDTO> listOutStuffDTO(@Param("userId") Integer userId);
}
