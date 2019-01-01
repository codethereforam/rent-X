package priv.thinkam.rentx.common.exception;

/**
 * 资源不存在异常
 *
 * @author thinkam
 * @date 2019/01/01
 */
public class ResourceNotFoundException extends BusinessException {
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
