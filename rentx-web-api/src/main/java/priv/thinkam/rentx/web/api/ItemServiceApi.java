package priv.thinkam.rentx.web.api;

import priv.thinkam.rentx.common.base.Response;
import priv.thinkam.rentx.web.api.dto.ItemApiDTO;
import priv.thinkam.rentx.web.api.dto.ItemDailyStatsApiDTO;
import priv.thinkam.rentx.web.api.dto.ItemStatusSelectApiDTO;
import priv.thinkam.rentx.web.api.param.ItemDailyStatsApiParam;

import java.util.List;

/**
 * item service api
 *
 * @author thinkam
 * @date 2019/02/15
 */
public interface ItemServiceApi {
	List<ItemApiDTO> listItemApiDTO();

	Response patchStatus(Integer itemId, Integer status, int userId);

	List<ItemDailyStatsApiDTO> listItemDailyStatsApiDTO(ItemDailyStatsApiParam itemDailyStatsApiParam);
	
	List<ItemStatusSelectApiDTO> listItemStatusSelectApiDTO();
}
