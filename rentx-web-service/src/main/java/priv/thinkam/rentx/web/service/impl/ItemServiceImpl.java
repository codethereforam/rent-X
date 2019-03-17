package priv.thinkam.rentx.web.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import priv.thinkam.rentx.common.base.Response;
import priv.thinkam.rentx.common.enums.ItemStatusEnum;
import priv.thinkam.rentx.common.util.BeanUtil;
import priv.thinkam.rentx.web.api.ItemServiceApi;
import priv.thinkam.rentx.web.api.dto.ItemApiDTO;
import priv.thinkam.rentx.web.api.dto.ItemDailyStatsApiDTO;
import priv.thinkam.rentx.web.api.dto.ItemStatusSelectApiDTO;
import priv.thinkam.rentx.web.api.param.ItemDailyStatsApiParam;
import priv.thinkam.rentx.web.dao.mapper.ItemMapper;
import priv.thinkam.rentx.web.service.ItemService;
import priv.thinkam.rentx.web.service.param.ItemDailyStatsParam;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ItemService Impl
 *
 * @author thinkam
 * @date 2019/02/16
 */
@Slf4j
@Service(version = "${rentx.web.service.version}")
public class ItemServiceImpl implements ItemServiceApi {
	@Resource
	private ItemMapper itemMapper;
	@Resource
	private ItemService itemService;

	@Override
	public List<ItemApiDTO> listItemApiDTO() {
		return itemMapper.listCompleteItemDTO().stream()
				.map(
						i -> new ItemApiDTO()
								.setItemId(i.getItemId())
								.setStuffName(i.getStuffName())
								.setApplyTime(i.getApplyTime())
								.setApprovalTime(i.getApprovalTime())
								.setPayTime(i.getPayTime())
								.setRentDay(i.getRentDay())
								.setEndTime(i.getEndTime())
								.setStatus(ItemStatusEnum.getByValue(i.getStatus()))
								.setOwnerName(i.getOwnerName())
								.setRenterName(i.getRenterName())
				)
				.collect(Collectors.toList());
	}

	@Override
	public Response patchStatus(Integer itemId, Integer status, int userId) {
		return itemService.patchStatus(itemId, status, userId);
	}

	@Override
	public List<ItemDailyStatsApiDTO> listItemDailyStatsApiDTO(ItemDailyStatsApiParam itemDailyStatsApiParam) {
		return itemService.listItemDailyStatsDTO(BeanUtil.map(itemDailyStatsApiParam, ItemDailyStatsParam.class)).stream()
				.map(dto -> BeanUtil.map(dto, ItemDailyStatsApiDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<ItemStatusSelectApiDTO> listItemStatusSelectApiDTO() {
		return Arrays.stream(ItemStatusEnum.values())
				.map(status -> new ItemStatusSelectApiDTO(status.getValue(), status.getName()))
				.collect(Collectors.toList());
	}


}
