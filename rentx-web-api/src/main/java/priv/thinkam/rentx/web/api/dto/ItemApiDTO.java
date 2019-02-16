package priv.thinkam.rentx.web.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import priv.thinkam.rentx.common.enums.ItemStatusEnum;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * item api dto
 *
 * @author thinkam
 * @date 2019/02/16
 */
@Data
@Accessors(chain = true)
public class ItemApiDTO implements Serializable {
	/**
	 * 租用项ID
	 */
	private Integer itemId;
	/**
	 * 物品名称
	 */
	private String stuffName;
	/**
	 * 申请时间
	 */
	private LocalDateTime applyTime;
	/**
	 * 租用日期
	 */
	private LocalDate createTime;

	/**
	 * 租用天数
	 */
	private Integer rentDay;
	/**
	 * 归还时间
	 */
	private LocalDateTime endTime;
	/**
	 * 状态（0：申请中；1：不通过；2：租用中；3： 已归还）
	 */
	private ItemStatusEnum status;
	/**
	 * 所有者名称
	 */
	private String ownerName;
	/**
	 * 租用者
	 */
	private String renterName;
}
