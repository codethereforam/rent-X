package priv.thinkam.rentx.common.util;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.BooleanUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * RedisTemplate test
 *
 * @author yanganyu
 * @date 2019/1/16 17:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Resource
	private RedisTemplate<String, Tuser> redisTemplate;

	@Test
	public void test1() {
		final String key = "foo";
		final String value = "bar";
		stringRedisTemplate.opsForValue().set(key, value);
		assertEquals(value, stringRedisTemplate.opsForValue().get(key));
		Boolean result = stringRedisTemplate.delete(key);
		assertTrue(BooleanUtils.toBoolean(result));
	}

	@Test
	public void test2() {
		Tuser tuser = new Tuser().setId(1).setName("用户1").setAge(18);
		String uuid = UUID.randomUUID().toString();
		redisTemplate.opsForValue().set(uuid, tuser);
		System.out.println(uuid);
		System.out.println(redisTemplate.opsForValue().get(uuid));
	}

	@Test
	public void test3() {
		Tuser tuser = new Tuser().setId(1).setName("用户1").setAge(18);
		String uuid = UUID.randomUUID().toString();
		stringRedisTemplate.opsForValue().set(uuid, JSONObject.toJSONString(tuser));
		System.out.println(uuid);
		System.out.println(stringRedisTemplate.opsForValue().get(uuid));
	}

	@Data
	@Accessors(chain = true)
	private static class Tuser {
		private Integer id;
		private String name;
		private Integer age;
	}
}