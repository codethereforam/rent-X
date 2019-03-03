package priv.thinkam.rentx.common.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * base entity
 *
 * @author yanganyu
 * @date 2019/1/8 17:06
 */
@Data
@EqualsAndHashCode
public class BaseEntity {
	/**
	 * ID
	 */
	private Integer id;

	/**
	 * 添加人ID
	 */
	private Integer addUserId;

	/**
	 * 添加时间
	 */
	private LocalDateTime addTime;

	/**
	 * 更新人ID
	 */
	private Integer updateUserId;

	/**
	 * 修改时间
	 */
	private LocalDateTime updateTime;

	/**
	 * 标识(是否有效 0有效；-1无效)
	 */
	private Boolean mark;

	/**
	 * 添加时补全add_user_id, add_time, update_user_id, update_time, mark
	 *
	 * @param userId 操作人
	 */
	public void completeAddParam(Integer userId) {
		LocalDateTime now = LocalDateTime.now();
		this.setAddUserId(userId);
		this.setAddTime(now);
		this.setUpdateUserId(userId);
		this.setUpdateTime(now);
		this.setMark(true);
	}
}
