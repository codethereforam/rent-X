package priv.thinkam.rentx.web.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import priv.thinkam.rentx.common.base.Response;
import priv.thinkam.rentx.web.base.WebBaseController;
import priv.thinkam.rentx.web.service.ItemService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 * 出租项 controller
 *
 * @author yanganyu
 * @date 2019-01-19
 */
@Slf4j
@Controller
@RequestMapping("/items")
public class ItemController extends WebBaseController {
	@Resource
	private ItemService itemService;

	@Value("${alipay.appId}")
	private String appId;
	@Value("${alipay.privateKey}")
	private String privateKey;
	@Value("${alipay.publicKey}")
	private String publicKey;
	@Value("${alipay.returnUrl}")
	private String returnUrl;
	@Value("${alipay.signType}")
	private String signType;
	@Value("${alipay.gatewayUrL}")
	private String gatewayUrL;

	private static final String OUT_TRADE_NO_PREFIX = "out-trade-no-";

	@PostMapping("/{id}/pay")
	@ResponseBody
	public Response pay(@PathVariable Integer id) throws AlipayApiException, IOException {
		PayOrder payOrder = new PayOrder();
		payOrder.setOut_trade_no(OUT_TRADE_NO_PREFIX + id);
		payOrder.setTotal_amount(itemService.getTotalDepositAndRental(id).toString());
		payOrder.setSubject("rent-X校园租赁 - 支付押金和租金");
		payOrder.setProduct_code("FAST_INSTANT_TRADE_PAY");
		AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrL, appId, privateKey, "json",
				StandardCharsets.UTF_8.name(),
				publicKey,
				signType);
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(returnUrl);
		alipayRequest.setBizContent(new ObjectMapper().writeValueAsString(payOrder));
		String result = alipayClient.pageExecute(alipayRequest).getBody();
		return Response.success(result);
	}

	@GetMapping("/pay/return")
	public String payReturn(HttpServletRequest request, HttpSession session) {
		String itemIdStr = request.getParameter("out_trade_no").substring(OUT_TRADE_NO_PREFIX.length());
		int itemId = Integer.parseInt(itemIdStr);
		itemService.finishPay(itemId, currentUserId(session));
		return redirect("/");
	}

	/**
	 * 我的租用
	 *
	 * <pre>
	 *     "申请中"的显示"取消申请"操作
	 * </pre>
	 *
	 * @return page
	 */
	@GetMapping("/in")
	public String startRentIn(Model model, Authentication authentication, HttpSession session) {
		model.addAttribute("personalItemVOList", itemService.listPersonItemVO(currentNonRootUserId(authentication, session)));
		return "my_rent_in";
	}

	/**
	 * 取消申请
	 *
	 * @param id item id
	 * @return Response
	 */
	@ResponseBody
	@PostMapping("/{id}/cancel-apply")
	public Response cancelApply(@PathVariable Integer id, HttpSession session) {
		return itemService.cancelApply(id, currentUserId(session));
	}

	@Data
	private class PayOrder implements Serializable {
		/**
		 * 订单名称
		 */
		private String subject;
		/**
		 * 商户网站唯一订单号
		 */
		private String out_trade_no;
		/**
		 * 付款金额
		 */
		private String total_amount;
		/**
		 * 销售产品码，与支付宝签约的产品码名称
		 */
		private String product_code;
	}
}
