package priv.thinkam.rentx.web.service.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import priv.thinkam.rentx.common.base.BaseVO;
import priv.thinkam.rentx.common.enums.ItemStatusEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author thinkam
 * @date 2019/02/10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class PersonalItemVO extends BaseVO {
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
	 * 审批时间
	 */
	private LocalDateTime approvalTime;

	/**
	 * 租用天数
	 */
	private Integer rentDay;
	/**
	 * 归还时间
	 */
	private LocalDateTime endTime;
	/**
	 * 状态（0：申请中；1：不通过；2:待支付；3：租用中；4： 已归还）
	 */
	private ItemStatusEnum status;
	/**
	 * 支付时间
	 */
	private LocalDateTime payTime;
}
