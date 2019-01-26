package priv.thinkam.rentx.web.api;

import priv.thinkam.rentx.web.api.bo.CategoryBO;

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
	 * @return CategoryBO list
	 * @author yanganyu
	 * @date 1/26/19 4:58 PM
	 */
	List<CategoryBO> listCategoryBO();
}
