package priv.thinkam.rentx.web.dao.mapper;

import priv.thinkam.rentx.web.dao.dto.ItemDTO;
import priv.thinkam.rentx.web.dao.dto.ItemDailyStatsDTO;
import priv.thinkam.rentx.web.dao.entity.Item;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import priv.thinkam.rentx.web.dao.query.ItemDailyStatsQuery;

import java.util.List;

/**
 * 出租项 Mapper
 *
 * @author yanganyu
 * @date 2019-01-19
 */
public interface ItemMapper extends BaseMapper<Item> {

	List<ItemDTO> listItemDTO(Integer userId);

	List<ItemDTO> listCompleteItemDTO();

	List<ItemDailyStatsDTO> listItemDailyStatsDTO(ItemDailyStatsQuery itemDailyStatsQuery);
}
