package priv.thinkam.rentx.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import priv.thinkam.rentx.dao.entity.User;

/**
 * <p>
 * 系统表 Mapper 接口
 * </p>
 *
 * @author yanganyu
 * @since 2019-01-10
 */
public interface UserMapper extends BaseMapper<User> {
	int testCount();
}
