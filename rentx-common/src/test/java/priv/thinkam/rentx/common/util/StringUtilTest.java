package priv.thinkam.rentx.common.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * String util test
 *
 * @author yanganyu
 * @date 2019/1/16 15:35
 */
public class StringUtilTest {

	@Test
	public void format() {
		String a = "A";
		String placeholder = "{}";
		String b = "B";
		String c = "C";
		assertEquals(a + b + c, StringUtil.format(a + placeholder + c, b));
	}
}