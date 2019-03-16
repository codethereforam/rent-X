package priv.thinkam.rentx.web.service;

import lombok.extern.slf4j.Slf4j;
import priv.thinkam.rentx.web.dao.entity.RoleMenu;
import priv.thinkam.rentx.web.dao.mapper.RoleMenuMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 角色菜单关系 service
 *
 * @author yanganyu
 * @date 2019-03-16
 */
@Slf4j
@Service
public class RoleMenuService extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IService<RoleMenu> {

}
