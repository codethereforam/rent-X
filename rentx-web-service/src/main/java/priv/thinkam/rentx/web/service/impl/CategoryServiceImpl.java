package priv.thinkam.rentx.web.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baidu.unbiz.fluentvalidator.*;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import priv.thinkam.rentx.common.base.Response;
import priv.thinkam.rentx.common.enums.EnableEnum;
import priv.thinkam.rentx.web.api.CategoryServiceApi;
import priv.thinkam.rentx.web.api.dto.CategoryApiDTO;
import priv.thinkam.rentx.web.api.param.CategoryApiParam;
import priv.thinkam.rentx.web.dao.entity.Category;
import priv.thinkam.rentx.web.dao.enums.CategoryLevelEnum;
import priv.thinkam.rentx.web.service.CategoryService;

import javax.annotation.Resource;
import javax.validation.Validation;
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
		Result result = FluentValidator.checkAll()
				.on(categoryApiParam, new HibernateSupportedValidator<CategoryApiParam>()
						.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
				.on(categoryApiParam.getName(), new ValidatorHandler<String>() {
					@Override
					public boolean validate(ValidatorContext context, String name) {
						int count = categoryService.count(
								new QueryWrapper<Category>().lambda()
										.eq(Category::getName, name)
										.eq(Category::getMark, EnableEnum.YES.getValue())
						);
						if (count > 0) {
							context.addError(ValidationError.create("已存在该类别"));
							return false;
						}
						return true;
					}
				})
				.on(categoryApiParam.getParentId(), new ValidatorHandler<Integer>() {
					@Override
					public boolean validate(ValidatorContext context, Integer parentId) {
						if (parentId == 0) {
							return true;
						}
						// 父类别必须存在且不能为三级类别
						int count = categoryService.count(
								new QueryWrapper<Category>().lambda()
										.eq(Category::getId, parentId)
										.ne(Category::getParentId, CategoryLevelEnum.THREE.getCode())
										.eq(Category::getMark, EnableEnum.YES.getValue())
						);
						if (count <= 0) {
							context.addError(ValidationError.create("父类别非法操作，否则请联系管理员"));
							return false;
						}
						return true;
					}
				})
				.doValidate()
				.result(ResultCollectors.toSimple());
		if (!result.isSuccess()) {
			log.info("add category invalid param: {}", result.getErrors());
			return new Response()
					.setCode(Response.CodeEnum.FAIL)
					.setMessage(result.getErrors().get(0));
		}
		Category category = new Category()
				.setName(categoryApiParam.getName())
				.setDescription(categoryApiParam.getDescription())
				.setParentId(categoryApiParam.getParentId())
				.setStatus(categoryApiParam.getStatus())
				.setLevel(categoryApiParam.getLevel());
		log.info("a category saved: {}", category);
		categoryService.save(category);
		return Response.SUCCESS;
	}

}
