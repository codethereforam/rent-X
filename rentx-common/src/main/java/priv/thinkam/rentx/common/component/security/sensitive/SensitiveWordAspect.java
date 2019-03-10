package priv.thinkam.rentx.common.component.security.sensitive;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import priv.thinkam.rentx.common.base.Constant;
import priv.thinkam.rentx.common.component.security.sensitive.plugin.SensitiveFilter;

import java.lang.reflect.Method;

/**
 * 敏感词过滤切面
 *
 * @author yanganyu
 * @date 2019/3/10 14:45
 */
@Aspect
@Component
public class SensitiveWordAspect {

	/**
	 * 敏感词过滤切面方法
	 *
	 * @param joinPoint joinPoint
	 * @return processed value
	 * @author yanganyu
	 * @date 2019/3/10 14:50
	 */
	@Around("within(priv.thinkam.rentx.common.base.BaseVO+) &&(execution(public String *.get*(..)) || execution(public String[] *.get*(..)))")
	public Object sensitiveWordFilter(ProceedingJoinPoint joinPoint) throws Throwable {
		Object value = joinPoint.proceed();
		if (value != null) {
			MethodSignature signature = (MethodSignature) joinPoint.getSignature();
			Method method = signature.getMethod();
			if (!method.isAnnotationPresent(MuteSensitive.class)) {
				if (String.class == value.getClass()) {
					value = SensitiveFilter.DEFAULT.filter((String) value, Constant.Separator.ASTERISK);
				} else if (value.getClass().isArray()) {
					String[] values = (String[]) value;
					for (int i = 0; i < values.length; i++) {
						values[i] = SensitiveFilter.DEFAULT.filter(values[i], Constant.Separator.ASTERISK);
					}
				}
			}
		}
		return value;
	}

}
