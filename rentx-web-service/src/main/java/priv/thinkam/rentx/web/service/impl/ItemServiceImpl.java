package priv.thinkam.rentx.web.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import priv.thinkam.rentx.common.enums.ItemStatusEnum;
import priv.thinkam.rentx.web.api.ItemServiceApi;
import priv.thinkam.rentx.web.api.dto.ItemApiDTO;
import priv.thinkam.rentx.web.dao.mapper.ItemMapper;

import javax.annotation.Resource;
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

	@Override
	public List<ItemApiDTO> listItemApiDTO() {
		return itemMapper.listCompleteItemDTO().stream()
				.map(
						i -> new ItemApiDTO()
								.setItemId(i.getItemId())
								.setStuffName(i.getStuffName())
								.setApplyTime(i.getApplyTime())
								.setCreateTime(i.getCreateTime())
								.setRentDay(i.getRentDay())
								.setEndTime(i.getEndTime())
								.setStatus(ItemStatusEnum.getByValue(i.getStatus()))
								.setOwnerName(i.getOwnerName())
								.setRenterName(i.getRenterName())
				)
				.collect(Collectors.toList());
	}
}
