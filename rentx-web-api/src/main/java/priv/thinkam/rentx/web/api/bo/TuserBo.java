package priv.thinkam.rentx.web.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author thinkam
 * @date 2019/01/21
 */
@Data
@Accessors(chain = true)
public class TuserBo implements Serializable {
	/**
	 * 名称
	 */
	private String name;

	/**
	 * 年龄
	 */
	private Integer age;
}
