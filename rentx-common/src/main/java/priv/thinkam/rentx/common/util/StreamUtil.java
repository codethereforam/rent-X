package priv.thinkam.rentx.common.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * stream util
 *
 * @author yanganyu
 * @date 2018/10/15 17:37
 */
@Slf4j
public class StreamUtil {
	private StreamUtil() {
	}

	/**
	 * stream Distinct by property
	 *
	 * @param keyExtractor keyExtractor
	 * @return java.util.function.Predicate<T>
	 * @author yanganyu
	 * @date 2018/10/15 17:37
	 * @see <a href="https://stackoverflow.com/questions/23699371/java-8-distinct-by-property">Java 8 Distinct by property</a>
	 */
	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Set<Object> seen = ConcurrentHashMap.newKeySet();
		return t -> seen.add(keyExtractor.apply(t));
	}
}
