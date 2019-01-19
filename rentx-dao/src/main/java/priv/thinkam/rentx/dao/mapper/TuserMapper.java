package priv.thinkam.rentx.dao.mapper;

import priv.thinkam.rentx.dao.entity.Tuser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 测试用户表 Mapper
 *
 * @author yanganyu
 * @date 2019-01-19
 */
public interface TuserMapper extends BaseMapper<Tuser> {
	int testCount();
}
