package priv.thinkam.rentx.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Kaptcha属性
 *
 * @author yanganyu
 * @date 2019/3/2 15:28
 */
@Data
@ConfigurationProperties(prefix = "kaptcha")
public class KaptchaProperty {
	private String border;
	private String imageWidth;
	private String imageHeight;
	private String sessionKey;
	private String textProducerCharLength;
	private String textProducerFontSize;
	private String textProducerFontColor;
	private String textProducerFontNames;
}
