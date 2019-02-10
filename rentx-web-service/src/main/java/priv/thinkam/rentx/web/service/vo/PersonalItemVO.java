package priv.thinkam.rentx.web.service.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import priv.thinkam.rentx.web.dao.enums.ItemStatusEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author thinkam
 * @date 2019/02/10
 */
@Data
@Accessors(chain = true)
public class PersonalItemVO {
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
}
