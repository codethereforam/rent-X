package priv.thinkam.rentx.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author thinkam
 * @date 2019/01/21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TuserResult implements Serializable {
	/**
	 * 名称
	 */
	private String name;

	/**
	 * 年龄
	 */
	private Integer age;
}
