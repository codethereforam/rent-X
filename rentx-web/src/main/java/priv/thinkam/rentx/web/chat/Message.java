package priv.thinkam.rentx.web.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * WebSocket 聊天消息类
 *
 * @author yanganyu
 * @date 2019/3/14 22:29
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Slf4j
public class Message {
	public static final String ENTER = "ENTER";
	public static final String SPEAK = "SPEAK";
	public static final String QUIT = "QUIT";
	/**
	 * 消息类型
	 */
	private String type;
	/**
	 * 发送人
	 */
	private String username;
	/**
	 * 发送消息
	 */
	private String msg;
	/**
	 * 在线用户数
	 */
	private int onlineCount;

	static String jsonStr(String type, String username, String msg, int onlineTotal) {
		String jsonStr = "";
		try {
			jsonStr = new ObjectMapper().writeValueAsString(new Message(type, username, msg, onlineTotal));
		} catch (JsonProcessingException e) {
			log.error("json序列化错误", e);
		}
		return jsonStr;
	}

}
