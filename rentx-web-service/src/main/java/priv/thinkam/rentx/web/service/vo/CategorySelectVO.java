package priv.thinkam.rentx.web.service.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import priv.thinkam.rentx.common.base.BaseVO;

/**
 * category select vo
 *
 * @author yanganyu
 * @date 2019/3/14 21:38
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class CategorySelectVO extends BaseVO {
	/**
	 * ID
	 */
	private Integer id;

	/**
	 * 类别名称
	 */
	private String name;
}
