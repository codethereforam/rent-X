package priv.thinkam.rentx.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import priv.thinkam.rentx.common.enums.EnableEnum;
import priv.thinkam.rentx.web.dao.entity.User;
import priv.thinkam.rentx.web.dao.entity.UserRole;
import priv.thinkam.rentx.web.dao.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import priv.thinkam.rentx.web.dao.mapper.UserRoleMapper;

import javax.annotation.Resource;

/**
 * 用户表 service
 *
 * @author yanganyu
 * @date 2019-01-19
 */
@Slf4j
@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IService<User> {
	@Resource
	private UserMapper userMapper;
	@Resource
	private UserRoleMapper userRoleMapper;

	/**
	 * whether username exists
	 *
	 * @param username username to check
	 * @return true: exists
	 */
	public boolean existsUsername(String username) {
		return this.count(
				new QueryWrapper<User>().lambda()
						.eq(User::getUsername, username)
						.eq(User::getMark, EnableEnum.YES.getValue())
		) > 0;
	}

	/**
	 * whether email exists
	 *
	 * @param email email
	 * @return true: exists
	 */
	public boolean existsEmail(String email) {
		return this.count(
				new QueryWrapper<User>().lambda()
						.eq(User::getEmail, email)
						.eq(User::getMark, EnableEnum.YES.getValue())
		) > 0;
	}

	/**
	 * save user and user-role relation
	 *
	 * @param user   user
	 * @param roleId role id
	 */
	@Transactional(rollbackFor = Exception.class)
	public void save(User user, Integer roleId) {
		userMapper.insert(user);
		UserRole userRole = new UserRole().setUserId(user.getId()).setRoleId(roleId);
		userRole.completeAddParam(0);
		userRoleMapper.insert(userRole);
	}
}
