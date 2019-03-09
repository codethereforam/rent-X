package priv.thinkam.rentx.common.component.security.xss;

import java.lang.annotation.*;

/**
 * 不做XSS防御的方法
 * <p>
 *     使用‘@Getter(onMethod_={@MuteXss})’方式集成lombok
 * </p>
 *
 * @author yanganyu
 * @date 2019/3/9 22:47
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MuteXss {
}
