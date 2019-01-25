package priv.thinkam.rentx.admin.service;

import lombok.extern.slf4j.Slf4j;
import priv.thinkam.rentx.admin.dao.entity.User;
import priv.thinkam.rentx.admin.dao.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户表 service
 *
 * @author yanganyu
 * @date 2019-01-25
 */
@Slf4j
@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IService<User> {
	@Resource
	private UserMapper userMapper;

	public List<User> listUsers() {
		return userMapper.list();
	}
}
