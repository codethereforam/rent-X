package priv.thinkam.rentx.common.util;

import org.slf4j.helpers.MessageFormatter;

/**
 * 字符串工具类
 *
 * @author thinkam
 * @date 2019/01/01
 */
public class StringUtil {
	/**
	 * elegant and efficient way to format string
	 *
	 * <pre>
	 * precondition: import slf4j-api or copy relative code in slf4j-api
	 * example:
	 * 		code: StringFormatterUtil.format("{} eats both {} and {}", "Tom", "apple", "cherry")
	 * 		result: "Tom eats both apple and cherry"
	 * </pre>
	 *
	 * @param format    The message pattern which will be parsed and formatted
	 * @param arguments An array of arguments to be substituted in place of formatting anchors
	 * @return formatted string
	 * @author thinkam
	 * @date 2018/7/22 18:35
	 */
	public static String format(String format, Object... arguments) {
		return MessageFormatter.arrayFormat(format, arguments).getMessage();
	}
}
