package priv.thinkam.rentx.web.service;

import lombok.extern.slf4j.Slf4j;
import priv.thinkam.rentx.web.dao.entity.Resource;
import priv.thinkam.rentx.web.dao.mapper.ResourceMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 资源 service
 *
 * @author yanganyu
 * @date 2019-01-23
 */
@Slf4j
@Service
public class ResourceService extends ServiceImpl<ResourceMapper, Resource> implements IService<Resource> {

}
