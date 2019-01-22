package priv.thinkam.rentx.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

/**
 * elasticSearch config
 *
 * @author yanganyu
 * @date 2019/1/22 14:12
 */
@Slf4j
@Configuration
public class ElasticSearchConfig implements InitializingBean {
	private static final String KEY = "es.set.netty.runtime.available.processors";

	static {
		System.setProperty(KEY, "false");
	}

	@Override
	public void afterPropertiesSet() {
		log.info("{}:{}", KEY, System.getProperty(KEY));
	}
}