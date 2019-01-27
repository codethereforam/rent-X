package priv.thinkam.rentx.common.config;

import com.baidu.unbiz.fluentvalidator.registry.impl.SpringApplicationContextRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Locale;

/**
 * Validator Config
 *
 * @author thinkam
 * @date 2019/01/27
 */
@Configuration
public class ValidatorConfig {

	@Bean
	public Validator hibernateValidator() {
		Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		return factory.getValidator();
	}

	@Bean
	public SpringApplicationContextRegistry springApplicationContextRegistry(ApplicationContext applicationContext) {
		SpringApplicationContextRegistry springApplicationContextRegistry = new SpringApplicationContextRegistry();
		springApplicationContextRegistry.setApplicationContext(applicationContext);
		return springApplicationContextRegistry;
	}

}