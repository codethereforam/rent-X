package priv.thinkam.rentx.web.dao.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author thinkam
 * @date 2019/02/10
 */
@Data
@Accessors(chain = true)
public class StuffDTO {
	private Integer stuffId;
	/**
	 * 类别名称
	 */
	private String categoryName;
	/**
	 * 物品名称
	 */
	private String stuffName;
	/**
	 * 所有者名称
	 */
	private String ownerName;
	/**
	 * 押金(rmb)
	 */
	private BigDecimal deposit;

	/**
	 * 租金（rmb/day）
	 */
	private BigDecimal rental;
	/**
	 * 物品状态（0:未租；1:申请租用；2:已租;3:不出租）
	 */
	private Integer status;
	/**
	 * 租用日期
	 */
	private LocalDate createTime;
	/**
	 * 租用天数
	 */
	private Integer rentDay;
	/**
	 * 租用者
	 */
	private String renterName;
}
