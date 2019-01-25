package priv.thinkam.rentx.admin.dao.mapper;

import priv.thinkam.rentx.admin.dao.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 用户表 Mapper
 *
 * @author yanganyu
 * @date 2019-01-25
 */
public interface UserMapper extends BaseMapper<User> {
	List<User> list();
}
