package priv.thinkam.rentx.common.util;

import com.baidu.unbiz.easymapper.MapperFactory;

/**
 * Bean Util
 *
 * @author thinkam
 * @date 2019/01/27
 */
public class BeanUtil {

	/**
	 * Bean Mapping util
	 *
	 * @param source      source
	 * @param targetClass targetClass
	 * @return target
	 * @author yanganyu
	 * @date 1/27/19 5:26 PM
	 */
	public static <S, T> T map(S source, Class<T> targetClass) {
		return MapperFactory.getCopyByRefMapper()
				.mapClass(source.getClass(), targetClass)
				.registerAndMap(source, targetClass);
	}

}
