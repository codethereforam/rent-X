package priv.thinkam.rentx.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import priv.thinkam.rentx.web.dao.dto.StuffDTO;
import priv.thinkam.rentx.web.dao.entity.Stuff;
import priv.thinkam.rentx.web.dao.enums.StuffStatusEnum;
import priv.thinkam.rentx.web.dao.mapper.StuffMapper;
import priv.thinkam.rentx.web.service.vo.StuffInVO;

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

}
