package priv.thinkam.rentx.web.service.validator.category;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import priv.thinkam.rentx.web.service.CategoryService;

import javax.annotation.Resource;

/**
 * Category Name Validator
 *
 * @author thinkam
 * @date 2019/01/27
 */
@Slf4j
@Component
public class CategoryNameValidator extends ValidatorHandler<String> implements Validator<String> {
	@Resource
	private CategoryService categoryService;

	@Override
	public boolean validate(ValidatorContext context, String name) {
		if (categoryService.existsName(name)) {
			context.addError(ValidationError.create("已存在该类别"));
			return false;
		}
		return true;
	}

}