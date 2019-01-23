package priv.thinkam.rentx.web.service;

import lombok.extern.slf4j.Slf4j;
import priv.thinkam.rentx.web.dao.entity.RoleResource;
import priv.thinkam.rentx.web.dao.mapper.RoleResourceMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 角色资源关系 service
 *
 * @author yanganyu
 * @date 2019-01-23
 */
@Slf4j
@Service
public class RoleResourceService extends ServiceImpl<RoleResourceMapper, RoleResource> implements IService<RoleResource> {

}
