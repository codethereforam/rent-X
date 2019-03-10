package priv.thinkam.rentx.common.component.alarm;

/**
 * 告警接口
 * <pre>
 *     定义一个接口，使用SPI技术调用，至于客户端怎么使用不用去官，可以基于邮件、短信等方式
 * </pre>
 *
 * @author yanganyu
 * @date 2019/3/10 17:40
 */
public interface AlarmApi {
	/**
	 * 发送告警
	 *
	 * @param content 告警内容
	 */
	void alarm(String content);
}
