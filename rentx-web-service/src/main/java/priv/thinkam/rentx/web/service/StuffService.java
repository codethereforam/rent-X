package priv.thinkam.rentx.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import priv.thinkam.rentx.common.base.Response;
import priv.thinkam.rentx.common.enums.EnableEnum;
import priv.thinkam.rentx.common.util.BeanUtil;
import priv.thinkam.rentx.web.dao.dto.StuffDTO;
import priv.thinkam.rentx.web.dao.entity.Stuff;
import priv.thinkam.rentx.web.dao.enums.StuffStatusEnum;
import priv.thinkam.rentx.web.dao.mapper.StuffMapper;
import priv.thinkam.rentx.web.service.param.StuffParam;
import priv.thinkam.rentx.web.service.vo.StuffInVO;
import priv.thinkam.rentx.web.service.vo.StuffOutVO;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 物品 service
 *
 * @author yanganyu
 * @date 2019-01-19
 */
@Slf4j
@Service
public class StuffService extends ServiceImpl<StuffMapper, Stuff> implements IService<Stuff> {
	@Resource
	private StuffMapper stuffMapper;

	public List<StuffInVO> listStuffInVO() {
		List<StuffDTO> stuffDTOList = stuffMapper.listStuffDTO();
		return stuffDTOList.stream().map(
				s -> new StuffInVO()
						.setCategoryName(s.getCategoryName())
						.setStuffName(s.getStuffName())
						.setOwnerName(s.getOwnerName())
						.setDeposit(s.getDeposit())
						.setRental(s.getRental())
						.setStatus(StuffStatusEnum.getByValue(s.getStatus()))
						.setShouldReturnDate(s.getCreateTime() == null ? null : s.getCreateTime().plusDays(s.getRentDay()))
		).collect(Collectors.toList());
	}

	public List<StuffOutVO> listStuffOutVO(Integer userId) {
		List<StuffDTO> stuffDTOList = stuffMapper.listOutStuffDTO(userId);
		return stuffDTOList.stream().map(
				s -> new StuffOutVO()
						.setId(s.getStuffId())
						.setCategoryName(s.getCategoryName())
						.setStuffName(s.getStuffName())
						.setRenterName(s.getRenterName())
						.setDeposit(s.getDeposit())
						.setRental(s.getRental())
						.setStatus(StuffStatusEnum.getByValue(s.getStatus()))
						.setShouldReturnDate(s.getCreateTime() == null ? null : s.getCreateTime().plusDays(s.getRentDay()))
		).collect(Collectors.toList());
	}

	public Response add(StuffParam stuffParam) {
		Stuff stuff = BeanUtil.map(stuffParam, Stuff.class);
		stuff.setAddUserId(stuff.getUserId());
		stuff.setUpdateUserId(stuff.getUserId());
		boolean success = this.save(stuff);
		log.info("a stuff saved: {}", stuff);
		if(success) {
			return Response.SUCCESS;
		} else {
			return Response.FAIL;
		}
	}

	public Response cancelRent(Integer stuffId, int userId) {
		if (this.count(
				new QueryWrapper<Stuff>().lambda()
						.eq(Stuff::getId, stuffId)
						.eq(Stuff::getMark, EnableEnum.YES.getValue())
		) <= 0) {
			return Response.fail("物品不存在");
		}
		Stuff stuff = new Stuff();
		stuff.setId(stuffId);
		stuff.setStatus(StuffStatusEnum.NOT.getCode());
		stuff.setUpdateUserId(userId);
		boolean success = this.updateById(stuff);
		log.info("a stuff updated: {}", stuff);
		if (success) {
			return Response.SUCCESS;
		} else {
			return Response.FAIL;
		}
	}
}
