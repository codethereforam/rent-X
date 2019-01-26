package priv.thinkam.rentx.web.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import priv.thinkam.rentx.web.api.CategoryServiceApi;
import priv.thinkam.rentx.web.api.dto.CategoryApiDTO;
import priv.thinkam.rentx.web.service.CategoryService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CategoryService Impl
 *
 * @author thinkam
 * @date 2019/01/26
 */
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
		return categoryService.list().stream()
				.map(c -> new CategoryApiDTO()
						.setId(c.getId())
						.setName(c.getName())
						.setDescription(c.getDescription())
						.setParentId(c.getParentId())
						.setLevel(c.getLevel())
						.setStatus(c.getStatus()))
				.collect(Collectors.toList());
	}

}
