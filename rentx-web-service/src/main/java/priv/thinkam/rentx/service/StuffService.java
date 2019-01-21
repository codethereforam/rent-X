package priv.thinkam.rentx.service;

import lombok.extern.slf4j.Slf4j;
import priv.thinkam.rentx.dao.entity.Stuff;
import priv.thinkam.rentx.dao.mapper.StuffMapper;
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
