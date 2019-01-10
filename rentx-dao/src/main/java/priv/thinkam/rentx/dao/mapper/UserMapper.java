package priv.thinkam.rentx.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import priv.thinkam.rentx.dao.entity.User;

/**
 * 用户表 Mapper
 *
 * @author yanganyu
 * @date 2019-01-10
 */
public interface UserMapper extends BaseMapper<User> {
	int testCount();
}
