package priv.thinkam.rentx.common.base;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 响应
 *
 * @author yanganyu
 * @date 1/1/19 10:16 PM
 */
@Data
@AllArgsConstructor
public class Response {
	/**
	 * 错误码，0:成功，-1:失败，其他失败
	 */
	private int code;
	/**
	 * 错误信息
	 */
	private String message;
	/**
	 * 数据
	 */
	private Object data;
}
