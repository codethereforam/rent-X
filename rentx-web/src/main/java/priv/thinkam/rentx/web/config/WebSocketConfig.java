package priv.thinkam.rentx.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * websocket config
 *
 * @author yanganyu
 * @date 2019/3/14 23:18
 */
@Configuration
public class WebSocketConfig {

	/**
	 * 用于扫描和注册所有携带ServerEndPoint注解的实例。
	 */
	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}
}
