package priv.thinkam.rentx.web.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

/**
 * 邮件服务
 *
 * @author yanganyu
 * @date 2019/3/3 17:37
 */
@Slf4j
@Service
public class MailService {
	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String mailUsername;
	@Value("${mail.senderName}")
	private String senderName;

	/**
	 * 同步发送邮件
	 *
	 * @param toAddr  to address
	 * @param subject subject
	 * @param content content
	 * @return true: send success
	 */
	public boolean sendMail(String toAddr, String subject, String content) {
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(mailUsername, senderName);
			helper.setTo(toAddr);
			helper.setSubject(subject);
			helper.setText(content, true);
			javaMailSender.send(message);
		} catch (UnsupportedEncodingException | MessagingException e) {
			log.error("邮件发送异常", e);
			return false;
		}
		return true;
	}

	/**
	 * 异步发送邮件
	 *
	 * @param toAddr  to address
	 * @param subject subject
	 * @param content content
	 */
	@Async
	public void sendEmailAsync(String toAddr, String subject, String content) {
		this.sendMail(toAddr, subject, content);
	}
}
