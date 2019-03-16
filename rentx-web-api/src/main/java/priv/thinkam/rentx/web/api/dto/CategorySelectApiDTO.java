package priv.thinkam.rentx.web.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * CategorySelect api dto
 *
 * @author thinkam
 * @date 2019/01/26
 */
@Data
@Accessors(chain = true)
public class CategorySelectApiDTO implements Serializable {
	/**
	 * ID
	 */
	private Integer id;

	/**
	 * 类别名称
	 */
	private String name;
}
