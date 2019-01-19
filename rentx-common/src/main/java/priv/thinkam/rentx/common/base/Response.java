package priv.thinkam.rentx.common.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

/**
 * 响应
 *
 * @author yanganyu
 * @date 1/1/19 10:16 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Response {
	/**
	 * 错误码，0:成功，-1:失败，其他失败
	 */
	private CodeEnum code;
	/**
	 * 错误信息
	 */
	private String message = StringUtils.EMPTY;
	/**
	 * 数据
	 */
	private Object data;

	/**
	 * code enum
	 *
	 * @author yanganyu
	 * @date 1/19/19 9:08 PM
	 */
	public enum CodeEnum {
		SUCCESS(0), FAIL(-1);
		private int value;

		CodeEnum(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
}
