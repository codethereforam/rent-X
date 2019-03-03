package priv.thinkam.rentx.common.base;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * 响应
 *
 * @author yanganyu
 * @date 1/1/19 10:16 PM
 */
public class Response<T> implements Serializable {
	/**
	 * default success message
	 */
	private static final String DEFAULT_SUCCESS_MESSAGE = "success";
	/**
	 * default fail message
	 */
	private static final String DEFAULT_FAIL_MESSAGE = "fail";
	/**
	 * default success response
	 */
	public static final Response SUCCESS = new Response(CodeEnum.SUCCESS, DEFAULT_SUCCESS_MESSAGE);
	/**
	 * default fail response
	 */
	public static final Response FAIL = new Response(CodeEnum.FAIL, DEFAULT_FAIL_MESSAGE);

	/**
	 * 错误码，0:成功，-1:失败，其他失败
	 */
	private CodeEnum code;
	/**
	 * 错误信息
	 */
	private String message;
	/**
	 * 错误字段
	 */
	private String field;
	/**
	 * 数据
	 */
	private T data;

	private Response(CodeEnum code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	private Response(CodeEnum code, String message) {
		this.code = code;
		this.message = message;
	}

	private Response(CodeEnum code, String message, String field) {
		this.code = code;
		this.message = message;
		this.field = field;
	}

	public static Response fail(String message) {
		return new Response(CodeEnum.FAIL, message);
	}

	public static Response fail(String message, String field) {
		return new Response(CodeEnum.FAIL, message, field);
	}

	public static <T> Response<T> success(T data) {
		return new Response<>(CodeEnum.SUCCESS, DEFAULT_SUCCESS_MESSAGE, data);
	}

	public Response.CodeEnum getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}

	public T getData() {
		return this.data;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

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

	@Override
	public String toString() {
		return "Response{" +
				"code=" + code +
				", message='" + message + '\'' +
				", field='" + field + '\'' +
				", data=" + data +
				'}';
	}
}
