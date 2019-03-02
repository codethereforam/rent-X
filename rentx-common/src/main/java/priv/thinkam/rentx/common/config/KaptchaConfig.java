package priv.thinkam.rentx.common.config;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * kaptcha config
 *
 * @author yanganyu
 * @date 2019/3/2 15:34
 */
@Configuration
@EnableConfigurationProperties(KaptchaProperty.class)
public class KaptchaConfig {
	@Autowired
	private KaptchaProperty kaptchaProperty;

	@Bean(name = "kaptchaProducer")
	public DefaultKaptcha getKaptchaBean() {
		DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
		Properties properties = new Properties();
		properties.setProperty(Constants.KAPTCHA_BORDER, kaptchaProperty.getBorder());
		properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, kaptchaProperty.getTextProducerFontColor());
		properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, kaptchaProperty.getTextProducerFontSize());
		properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, kaptchaProperty.getTextProducerFontNames());
		properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, kaptchaProperty.getTextProducerCharLength());
		properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH, kaptchaProperty.getImageWidth());
		properties.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT, kaptchaProperty.getImageHeight());
		properties.setProperty(Constants.KAPTCHA_SESSION_KEY, kaptchaProperty.getSessionKey());
		Config config = new Config(properties);
		defaultKaptcha.setConfig(config);
		return defaultKaptcha;
	}
}
