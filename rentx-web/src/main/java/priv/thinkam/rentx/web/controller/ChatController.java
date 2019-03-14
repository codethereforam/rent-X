package priv.thinkam.rentx.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 聊天控制器
 *
 * @author yanganyu
 * @date 2019/3/14 22:20
 */
@Slf4j
@Controller
@RequestMapping("/chat")
public class ChatController {

	/**
	 * 聊天界面
	 */
	@GetMapping("/index")
	public String index(String username, HttpServletRequest request, Model model) throws UnknownHostException {
		if (StringUtils.isBlank(username)) {
			username = "匿名用户";
		}
		model.addAttribute("username", username);
		model.addAttribute("webSocketUrl",
				"ws://" + InetAddress.getLocalHost().getHostAddress() + ":" + request.getServerPort() + request.getContextPath() + "/chatServer");
		return "chat";
	}

}
