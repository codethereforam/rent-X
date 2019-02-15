package priv.thinkam.rentx.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import priv.thinkam.rentx.common.base.Response;
import priv.thinkam.rentx.common.enums.EnableEnum;
import priv.thinkam.rentx.web.dao.entity.Item;
import priv.thinkam.rentx.web.dao.entity.Stuff;
import priv.thinkam.rentx.web.dao.enums.ItemStatusEnum;
import priv.thinkam.rentx.web.dao.enums.StuffStatusEnum;
import priv.thinkam.rentx.web.dao.mapper.ItemMapper;
import priv.thinkam.rentx.web.service.vo.PersonalItemVO;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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
	@Resource
	private StuffService stuffService;

	public List<PersonalItemVO> listPersonItemVO(Integer userId) {
		return itemMapper.listItemDTO(userId).stream()
				.map(
						i -> new PersonalItemVO()
								.setItemId(i.getItemId())
								.setStuffName(i.getStuffName())
								.setApplyTime(i.getApplyTime())
								.setCreateTime(i.getCreateTime())
								.setRentDay(i.getRentDay())
								.setEndTime(i.getEndTime())
								.setStatus(ItemStatusEnum.getByValue(i.getStatus()))
				)
				.collect(Collectors.toList());
	}

	public Response rent(Integer stuffId, int userId, Integer rentDay) {
		// update stuff
		if (stuffService.count(
				new QueryWrapper<Stuff>().lambda()
						.eq(Stuff::getId, stuffId)
						.eq(Stuff::getMark, EnableEnum.YES.getValue())
		) <= 0) {
			return Response.fail("物品不存在");
		}
		Stuff stuff = new Stuff();
		stuff.setId(stuffId);
		stuff.setStatus(StuffStatusEnum.APPLY.getCode());
		stuff.setUpdateUserId(userId);
		boolean updateStuffSuccess = stuffService.updateById(stuff);
		log.info("a stuff updated: {}", stuff);
		if (!updateStuffSuccess) {
			return Response.fail("更新物品失败");
		}
		// insert item
		Item item = new Item();
		item.setUserId(userId);
		item.setStuffId(stuffId);
		item.setRentDay(rentDay);
		item.setApplyTime(LocalDateTime.now());
		item.setStatus(ItemStatusEnum.APPLYING.getCode());
		item.setAddUserId(userId);
		item.setUpdateUserId(userId);
		boolean success = this.save(item);
		log.info("an item saved: {}", item);
		if(success) {
			return Response.SUCCESS;
		} else {
			return Response.FAIL;
		}
	}

	public Response cancelApply(Integer itemId, int userId) {
		Item item = this.getOne(
				new QueryWrapper<Item>().lambda()
						.eq(Item::getId, itemId)
						.eq(Item::getMark, EnableEnum.YES.getValue())
		);
		if(item == null) {
			return Response.fail("租用项不存在");
		}
		Item updateItem = new Item();
		updateItem.setId(itemId);
		updateItem.setUpdateUserId(userId);
		updateItem.setMark(false);
		boolean updateItemSuccess = this.updateById(updateItem);
		if(!updateItemSuccess) {
			return Response.fail("更新租用项失败");
		}
		log.info("an item updated: {}", updateItem);
		Stuff updateStuff = new Stuff();
		updateStuff.setId(item.getStuffId());
		updateStuff.setStatus(StuffStatusEnum.HAVE_NOT.getCode());
		updateStuff.setUpdateUserId(userId);
		boolean updateStuffSuccess = stuffService.updateById(updateStuff);
		if(!updateStuffSuccess) {
			return Response.fail("更新物品失败");
		}
		log.info("a stuff updated: {}", updateStuff);
		return Response.SUCCESS;
	}
}
