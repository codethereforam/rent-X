package priv.thinkam.rentx.service;

import lombok.extern.slf4j.Slf4j;
import priv.thinkam.rentx.dao.entity.Item;
import priv.thinkam.rentx.dao.mapper.ItemMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 出租项 service
 *
 * @author yanganyu
 * @date 2019-01-19
 */
@Slf4j
@Service
public class ItemService extends ServiceImpl<ItemMapper, Item> implements IService<Item> {

}
