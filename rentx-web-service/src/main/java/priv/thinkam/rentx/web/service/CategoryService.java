package priv.thinkam.rentx.web.service;

import lombok.extern.slf4j.Slf4j;
import priv.thinkam.rentx.web.dao.entity.Category;
import priv.thinkam.rentx.web.dao.mapper.CategoryMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 类别 service
 *
 * @author yanganyu
 * @date 2019-01-19
 */
@Slf4j
@Service
public class CategoryService extends ServiceImpl<CategoryMapper, Category> implements IService<Category> {

}
