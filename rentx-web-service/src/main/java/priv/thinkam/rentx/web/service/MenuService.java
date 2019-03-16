package priv.thinkam.rentx.web.service;

import lombok.extern.slf4j.Slf4j;
import priv.thinkam.rentx.web.dao.entity.Menu;
import priv.thinkam.rentx.web.dao.mapper.MenuMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 菜单 service
 *
 * @author yanganyu
 * @date 2019-03-16
 */
@Slf4j
@Service
public class MenuService extends ServiceImpl<MenuMapper, Menu> implements IService<Menu> {

}
