package priv.thinkam.rentx.web.api;

import priv.thinkam.rentx.common.base.Response;
import priv.thinkam.rentx.web.api.dto.CategoryApiDTO;
import priv.thinkam.rentx.web.api.param.CategoryApiParam;

import java.util.List;

/**
 * CategoryService Api
 *
 * @author thinkam
 * @date 2019/01/26
 */
public interface CategoryServiceApi {
	/**
	 * 获取所有类别信息
	 *
	 * @return CategoryApiDTO list
	 * @author yanganyu
	 * @date 1/26/19 4:58 PM
	 */
	List<CategoryApiDTO> listCategoryApiDTO();

	/**
	 * 添加类别
	 *
	 * @param categoryApiParam categoryApiParam
	 * @return priv.thinkam.rentx.common.base.Response
	 * @author yanganyu
	 * @date 1/27/19 12:39 AM
	 */
	Response add(CategoryApiParam categoryApiParam);
}
