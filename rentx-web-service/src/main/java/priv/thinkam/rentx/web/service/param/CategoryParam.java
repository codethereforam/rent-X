package priv.thinkam.rentx.web.service.param;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import lombok.Data;
import lombok.experimental.Accessors;
import priv.thinkam.rentx.web.service.validator.category.CategoryNameValidator;
import priv.thinkam.rentx.web.service.validator.category.CategoryParentIdValidator;
import priv.thinkam.rentx.web.service.validator.category.CategoryValidatorGroup;

import javax.validation.constraints.*;

/**
 * Category Param
 *
 * @author thinkam
 * @date 2019/01/26
 */
@Data
@Accessors(chain = true)
public class CategoryParam {
	/**
	 * 类别编号
	 */
	private Integer id;

	/**
	 * 类别名称
	 */
	@NotBlank(message = "类别名称不能为空", groups = {CategoryValidatorGroup.Add.class,CategoryValidatorGroup.Modify.class})
	@Size(min = 1, max = 20, message = "类别名称长度应为1-20个字符", groups = {CategoryValidatorGroup.Add.class,CategoryValidatorGroup.Modify.class})
	@Pattern(regexp = "[0-9a-zA-Z\u4e00-\u9fa5_]+", message = "类别名称仅支持中英文、数字和下划线", groups = {CategoryValidatorGroup.Add.class,CategoryValidatorGroup.Modify.class})
	@FluentValidate(value = {CategoryNameValidator.class}, groups = CategoryValidatorGroup.Add.class)
	private String name;

	/**
	 * 类别描述
	 */
	@Size(max = 255, message = "类别描述输入字符过多，超过255个字符", groups = {CategoryValidatorGroup.Add.class,CategoryValidatorGroup.Modify.class})
	private String description;

	/**
	 * 父类别编号（0代表是根类别）
	 */
	@NotNull(message = "父类别非法操作，否则请联系管理员", groups = {CategoryValidatorGroup.Add.class,CategoryValidatorGroup.Modify.class})
	@FluentValidate(value = {CategoryParentIdValidator.class}, groups = {CategoryValidatorGroup.Add.class,CategoryValidatorGroup.Modify.class})
	private Integer parentId;

	/**
	 *  类别层次（只能为1或2或3）
	 */
	@NotNull(message = "级别非法操作，否则请联系管理员", groups = {CategoryValidatorGroup.Add.class,CategoryValidatorGroup.Modify.class})
	@Min(value = 1, message = "级别非法操作，否则请联系管理员", groups = {CategoryValidatorGroup.Add.class,CategoryValidatorGroup.Modify.class})
	@Max(value = 3, message = "级别非法操作，否则请联系管理员", groups = {CategoryValidatorGroup.Add.class,CategoryValidatorGroup.Modify.class})
	private Integer level;

	/**
	 * 状态(1:启用, 0:禁用)
	 */
	@NotNull(message = "是否启用字段非法操作，否则请联系管理员", groups = {CategoryValidatorGroup.Add.class,CategoryValidatorGroup.Modify.class})
	private Boolean status;
}
