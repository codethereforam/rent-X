package priv.thinkam.rentx.service;

import lombok.extern.slf4j.Slf4j;
import priv.thinkam.rentx.dao.entity.User;
import priv.thinkam.rentx.dao.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户表 service
 *
 * @author yanganyu
 * @date 2019-01-19
 */
@Slf4j
@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IService<User> {

}
