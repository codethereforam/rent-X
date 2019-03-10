package priv.thinkam.rentx.common.component.alarm;

import org.springframework.stereotype.Service;

import java.util.ServiceLoader;

/**
 * 告警服务
 *
 * @author yanganyu
 * @date 2019/3/10 17:43
 */
@Service
public class AlarmService {
	private ServiceLoader<AlarmApi> loader = ServiceLoader.load(AlarmApi.class);

	/**
	 * 发送告警
	 *
	 * @param content 告警内容
	 */
	public void alarm(String content) {
		for (AlarmApi alarmApi : loader) {
			alarmApi.alarm(content);
		}
	}

}