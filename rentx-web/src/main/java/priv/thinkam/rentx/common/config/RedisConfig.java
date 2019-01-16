package priv.thinkam.rentx.common.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * redis config
 *
 * @author yanganyu
 * @date 2019/1/16 16:54
 */
@Configuration
public class RedisConfig {

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.setKeySerializer(RedisSerializer.string());
		redisTemplate.setHashKeySerializer(RedisSerializer.string());
		redisTemplate.setValueSerializer(new GenericFastJsonRedisSerializer());
		redisTemplate.setHashValueSerializer(new GenericFastJsonRedisSerializer());
		return redisTemplate;
	}

}
