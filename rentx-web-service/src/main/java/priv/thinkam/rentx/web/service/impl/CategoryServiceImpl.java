package priv.thinkam.rentx.web.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import priv.thinkam.rentx.common.base.Response;
import priv.thinkam.rentx.common.enums.EnableEnum;
import priv.thinkam.rentx.common.util.BeanUtil;
import priv.thinkam.rentx.web.api.CategoryServiceApi;
import priv.thinkam.rentx.web.api.dto.CategoryApiDTO;
import priv.thinkam.rentx.web.api.dto.CategorySelectApiDTO;
import priv.thinkam.rentx.web.api.param.CategoryApiParam;
import priv.thinkam.rentx.web.dao.entity.Category;
import priv.thinkam.rentx.web.service.CategoryService;
import priv.thinkam.rentx.web.service.param.CategoryParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CategoryService Impl
 *
 * @author thinkam
 * @date 2019/01/26
 */
@Slf4j
@Service(version = "${rentx.web.service.version}")
public class CategoryServiceImpl implements CategoryServiceApi {
	@Resource
	private CategoryService categoryService;

	/**
	 * 获取所有类别信息
	 *
	 * @return CategoryApiDTO list
	 * @author yanganyu
	 * @date 1/26/19 4:58 PM
	 */
	@Override
	public List<CategoryApiDTO> listCategoryApiDTO() {
		return categoryService.list(
				new QueryWrapper<Category>().lambda().eq(Category::getMark, EnableEnum.YES.getValue())
		).stream().map(c -> BeanUtil.map(c, CategoryApiDTO.class))
				.collect(Collectors.toList());
	}

	/**
	 * 添加类别
	 *
	 * @param categoryApiParam categoryApiParam
	 * @return priv.thinkam.rentx.common.base.Response
	 * @author yanganyu
	 * @date 1/27/19 12:39 AM
	 */
	@Override
	public Response add(CategoryApiParam categoryApiParam) {
		return categoryService.add(BeanUtil.map(categoryApiParam, CategoryParam.class));
	}

	/**
	 * 修改类别
	 *
	 * @param categoryApiParam categoryApiParam
	 * @return priv.thinkam.rentx.common.base.Response
	 * @author yanganyu
	 * @date 1/27/19 6:53 PM
	 */
	@Override
	public Response modify(CategoryApiParam categoryApiParam) {
		return categoryService.modify(BeanUtil.map(categoryApiParam, CategoryParam.class));
	}

	@Override
	public Response delete(Integer id) {
		return categoryService.delete(id);
	}

	@Override
	public List<CategorySelectApiDTO> listCategorySelectApiDTO() {
		return categoryService.listThreeLevelCategory().stream()
				.map(c -> BeanUtil.map(c, CategorySelectApiDTO.class))
				.collect(Collectors.toList());
	}
}
