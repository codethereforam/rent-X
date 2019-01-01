package priv.thinkam.rentx.common.exception;

/**
 * 资源已存在异常
 *
 * @author thinkam
 * @date 2019/01/01
 */
public class ResourceAlreadyExistsException extends BusinessException {
	public ResourceAlreadyExistsException(String message) {
		super(message);
	}
}
