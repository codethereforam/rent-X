package priv.thinkam.rentx.web.service;

import com.baidu.unbiz.fluentvalidator.*;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.baidu.unbiz.fluentvalidator.registry.Registry;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import priv.thinkam.rentx.common.base.Response;
import priv.thinkam.rentx.common.enums.EnableEnum;
import priv.thinkam.rentx.common.util.BeanUtil;
import priv.thinkam.rentx.web.dao.entity.Category;
import priv.thinkam.rentx.web.dao.mapper.CategoryMapper;
import priv.thinkam.rentx.web.service.param.CategoryParam;
import priv.thinkam.rentx.web.service.validator.category.CategoryValidatorGroup;

import javax.annotation.Resource;

/**
 * 类别 service
 *
 * @author yanganyu
 * @date 2019-01-19
 */
@Slf4j
@Service
public class CategoryService extends ServiceImpl<CategoryMapper, Category> implements IService<Category> {
	@Resource
	private javax.validation.Validator hibernateValidator;
	@Resource
	private Registry springApplicationContextRegistry;

	/**
	 * whether category name exists
	 *
	 * @param name category name
	 * @return true: exists
	 */
	public boolean existsName(String name) {
		return this.count(
				new QueryWrapper<Category>().lambda()
						.eq(Category::getName, name)
						.eq(Category::getMark, EnableEnum.YES.getValue())
		) > 0;
	}

	/**
	 * whether category name equals {id} and level not equals {level}
	 *
	 * @param id    category id
	 * @param level category level
	 * @return true: exists
	 */
	public boolean existsLevelNotEquals(Integer id, int level) {
		return this.count(
				new QueryWrapper<Category>().lambda()
						.eq(Category::getId, id)
						.ne(Category::getParentId, level)
						.eq(Category::getMark, EnableEnum.YES.getValue())
		) > 0;
	}

	/**
	 * 添加类别
	 *
	 * @param categoryParam categoryParam
	 * @return priv.thinkam.rentx.common.base.Response
	 * @author yanganyu
	 * @date 1/27/19 1:33 PM
	 */
	public Response add(CategoryParam categoryParam) {
		Result result = FluentValidator.checkAll(CategoryValidatorGroup.Add.class)
				.on(categoryParam, new HibernateSupportedValidator<CategoryParam>()
						.setHiberanteValidator(hibernateValidator))
				.configure(springApplicationContextRegistry)
				.on(categoryParam)
				.doValidate()
				.result(ResultCollectors.toSimple());
		if (!result.isSuccess()) {
			log.info("add category invalid param: {}", result.getErrors());
			return Response.fail(result.getErrors().get(0));
		}
		Category category = BeanUtil.map(categoryParam, Category.class);
		this.save(category);
		log.info("a category saved: {}", category);
		return Response.SUCCESS;
	}

	/**
	 * 修改类别
	 *
	 * @param categoryParam categoryParam
	 * @return Response
	 */
	public Response modify(CategoryParam categoryParam) {
		final String categoryIdErrorMsg = "类别编号非法操作，否则请联系管理员";
		if (categoryParam.getId() == null) {
			return Response.fail(categoryIdErrorMsg);
		}
		Category originCategory = this.getById(categoryParam.getId());
		if (originCategory == null) {
			return Response.fail(categoryIdErrorMsg);
		}
		Result result = FluentValidator.checkAll(CategoryValidatorGroup.Modify.class)
				.on(categoryParam, new HibernateSupportedValidator<CategoryParam>()
						.setHiberanteValidator(hibernateValidator))
				.configure(springApplicationContextRegistry)
				.on(categoryParam)
				.on(categoryParam.getLevel(), new ValidatorHandler<Integer>() {
					@Override
					public boolean validate(ValidatorContext context, Integer level) {
						// 校验不能降级(父类别级别要比自己原本级别小)
						if (level > originCategory.getLevel()) {
							context.addError(ValidationError.create("级别不能降级"));
							return false;
						}
						return true;
					}
				})
				.doValidate()
				.result(ResultCollectors.toSimple());
		if (!result.isSuccess()) {
			log.info("modify category invalid param: {}", result.getErrors());
			return Response.fail(result.getErrors().get(0));
		}
		Category category = BeanUtil.map(categoryParam, Category.class);
		this.updateById(category);
		log.info("a category modified: {}", category);
		return Response.SUCCESS;
	}
}
