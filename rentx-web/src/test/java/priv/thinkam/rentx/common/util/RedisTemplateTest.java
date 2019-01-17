package priv.thinkam.rentx.common.util;

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
		delete(key);
	}

	@Test
	public void test2() {
		Tuser tuser = new Tuser().setId(1).setName("用户1").setAge(18);
		String key = UUID.randomUUID().toString();
		redisTemplate.opsForValue().set(key, tuser);
		assertEquals(tuser, redisTemplate.opsForValue().get(key));
		delete(key);
	}

	@Test
	public void test3() {
		Tuser tuser = new Tuser().setId(1).setName("用户1").setAge(18);
		String key = UUID.randomUUID().toString();
		final String hashKeyId = "id";
		final String hashKeyName = "name";
		final String hashKeyAge = "age";
		redisTemplate.opsForHash().put(key, hashKeyId, tuser.getId());
		redisTemplate.opsForHash().put(key, hashKeyName, tuser.getName());
		redisTemplate.opsForHash().put(key, hashKeyAge, tuser.getAge());
		assertEquals(tuser.getId(), redisTemplate.opsForHash().get(key, hashKeyId));
		assertEquals(tuser.getName(), redisTemplate.opsForHash().get(key, hashKeyName));
		assertEquals(tuser.getAge(), redisTemplate.opsForHash().get(key, hashKeyAge));
		delete(key);
	}

	private void delete(String key) {
		Boolean result = redisTemplate.delete(key);
		assertTrue(BooleanUtils.toBoolean(result));
	}

	@Data
	@Accessors(chain = true)
	private static class Tuser {
		private Integer id;
		private String name;
		private Integer age;
	}
}