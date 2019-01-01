package priv.thinkam.rentx.common.exception;

/**
 * 资源无权限访问异常
 *
 * @author thinkam
 * @date 2019/01/01
 */
public class ResourceForbiddenException extends BusinessException {
	public ResourceForbiddenException(String message) {
		super(message);
	}
}
