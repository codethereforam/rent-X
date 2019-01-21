package priv.thinkam.rentx.common.base;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
public class Response<T> {
	/**
	 * 错误码，0:成功，-1:失败，其他失败
	 */
	private CodeEnum code;
	/**
	 * 错误信息
	 */
	private String message;
	/**
	 * 数据
	 */
	private T data;

	/**
	 * code enum
	 *
	 * @author yanganyu
	 * @date 1/19/19 9:08 PM
	 */
	public enum CodeEnum {
		/**
		 * 成功
		 */
		SUCCESS(0),
		/**
		 * 失败
		 */
		FAIL(-1);
		private int value;

		CodeEnum(int value) {
			this.value = value;
		}

		@JsonValue
		public int getValue() {
			return value;
		}
	}
}
