package priv.thinkam.rentx.web.service;

import lombok.extern.slf4j.Slf4j;
import priv.thinkam.rentx.web.dao.entity.Role;
import priv.thinkam.rentx.web.dao.mapper.RoleMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 角色 service
 *
 * @author yanganyu
 * @date 2019-01-23
 */
@Slf4j
@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> implements IService<Role> {

}
