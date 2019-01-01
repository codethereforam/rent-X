package priv.thinkam.rentx.common.exception;

/**
 * 必须要处理的业务异常
 *
 * @author thinkam
 * @date 2019/01/01
 */
public abstract class BusinessException extends RuntimeException {
	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}
}
