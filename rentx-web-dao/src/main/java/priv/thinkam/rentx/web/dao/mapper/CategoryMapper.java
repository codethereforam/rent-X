package priv.thinkam.rentx.web.dao.mapper;

import org.apache.ibatis.annotations.Param;
import priv.thinkam.rentx.web.dao.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Set;

/**
 * 类别 Mapper
 *
 * @author yanganyu
 * @date 2019-01-19
 */
public interface CategoryMapper extends BaseMapper<Category> {

	void batchDelete(@Param("set") Set<Integer> ids);

}
