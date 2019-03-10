package priv.thinkam.rentx.web.service;

import priv.thinkam.rentx.common.component.alarm.AlarmApi;
import priv.thinkam.rentx.common.util.ApplicationContextUtil;

/**
 * 用邮件实现的告警
 *
 * @author yanganyu
 * @date 2019/3/10 17:45
 */
public class MailAlarm implements AlarmApi {
	private static MailService mailService;

	/**
	 * 发送告警
	 *
	 * @param content 告警内容
	 */
	@Override
	public void alarm(String content) {
		if (mailService == null) {
			mailService = ApplicationContextUtil.getBean(MailService.class);
		}
		mailService.sendEmailAsync(" codethereforam@gmail.com " , "告警" , content);
	}
}
