package priv.thinkam.rentx.common.base;

import com.alibaba.fastjson.serializer.JSONSerializable;
import com.alibaba.fastjson.serializer.JSONSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;

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
	private String message = StringUtils.EMPTY;
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
	public enum CodeEnum implements JSONSerializable {
		SUCCESS(0), FAIL(-1);
		private int value;

		CodeEnum(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		@Override
		public void write(JSONSerializer serializer, Object fieldName, Type fieldType, int features) {
			serializer.write(this.getValue());
		}
	}
}
