package priv.thinkam.rentx.web.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * category BO
 *
 * @author thinkam
 * @date 2019/01/26
 */
@Data
@Accessors(chain = true)
public class CategoryBO implements Serializable {
	/**
	 * ID
	 */
	private Integer id;

	/**
	 * 类别名称
	 */
	private String name;

	/**
	 * 类别描述
	 */
	private String description;

	/**
	 * 父类别编号（0代表是根类别）
	 */
	private Integer parentId;

	/**
	 *  类别层次（只能为1或2或3）
	 */
	private Integer level;

	/**
	 * 状态(1:启用, 0:禁用)
	 */
	private Boolean status;
}
