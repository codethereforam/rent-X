package priv.thinkam.rentx.common.component.security.xss;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

import java.lang.reflect.Method;

/**
 * Xss切面
 *
 * @author thinkam
 * @date 2019/01/01
 */
@Aspect
@Component
public class XssAspect {

	/**
	 * Xss切面方法
	 *
	 * <pre>
	 * 所有BaseVO子类种的'String getXxx()'或'String[] getXxx()'被调用时拦截，做html转义处理
	 * 如果方法有@MuteXss注解，则不处理
	 * </pre>
	 *
	 * @param joinPoint joinPoint
	 * @return processed value
	 * @throws Throwable Throwable
	 */
	@Around("within(priv.thinkam.rentx.common.base.BaseVO+) &&(execution(public String *.get*(..)) || execution(public String[] *.get*(..)))")
	public Object htmlEscape(ProceedingJoinPoint joinPoint) throws Throwable {
		Object value = joinPoint.proceed();
		if (value != null) {
			MethodSignature signature = (MethodSignature) joinPoint.getSignature();
			Method method = signature.getMethod();
			if (!method.isAnnotationPresent(MuteXss.class)) {
				if (String.class == value.getClass()) {
					value = HtmlUtils.htmlEscape((String) value);
				} else if (value.getClass().isArray()) {
					String[] values = (String[]) value;
					for (int i = 0; i < values.length; i++) {
						values[i] = HtmlUtils.htmlEscape(values[i]);
					}
				}
			}
		}
		return value;
	}

}
