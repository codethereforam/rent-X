package priv.thinkam.rentx.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import priv.thinkam.rentx.dao.entity.User;
import priv.thinkam.rentx.dao.mapper.UserMapper;

import javax.annotation.Resource;

/**
 * <p>
 * 系统表 服务实现类
 * </p>
 *
 * @author yanganyu
 * @since 2019-01-10
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IService<User> {
	@Resource
	private UserMapper userMapper;

	public int testCount() {
		return userMapper.testCount();
	}
}
