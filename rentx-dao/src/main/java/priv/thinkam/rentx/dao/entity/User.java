package priv.thinkam.rentx.dao.entity;

import priv.thinkam.rentx.common.base.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统表
 * </p>
 *
 * @author yanganyu
 * @since 2019-01-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class User extends BaseEntity {

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 年龄
	 */
	private Integer age;

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
}
