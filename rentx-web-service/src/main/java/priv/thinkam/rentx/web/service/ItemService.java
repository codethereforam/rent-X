package priv.thinkam.rentx.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import priv.thinkam.rentx.web.dao.entity.Item;
import priv.thinkam.rentx.web.dao.enums.ItemStatusEnum;
import priv.thinkam.rentx.web.dao.mapper.ItemMapper;
import priv.thinkam.rentx.web.service.vo.PersonalItemVO;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 出租项 service
 *
 * @author yanganyu
 * @date 2019-01-19
 */
@Slf4j
@Service
public class ItemService extends ServiceImpl<ItemMapper, Item> implements IService<Item> {
	@Resource
	private ItemMapper itemMapper;

	public List<PersonalItemVO> listPersonItemVO(Integer userId) {
		return itemMapper.listItemDTO(userId).stream()
				.map(
						i -> new PersonalItemVO().setStuffName(i.getStuffName())
								.setApplyTime(i.getApplyTime())
								.setCreateTime(i.getCreateTime())
								.setRentDay(i.getRentDay())
								.setEndTime(i.getEndTime())
								.setStatus(ItemStatusEnum.getByValue(i.getStatus()))
				)
				.collect(Collectors.toList());
	}

}
