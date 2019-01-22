package priv.thinkam.rentx.web.service;

import lombok.extern.slf4j.Slf4j;
import priv.thinkam.rentx.web.dao.entity.Stuff;
import priv.thinkam.rentx.web.dao.mapper.StuffMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 物品 service
 *
 * @author yanganyu
 * @date 2019-01-19
 */
@Slf4j
@Service
public class StuffService extends ServiceImpl<StuffMapper, Stuff> implements IService<Stuff> {

}
