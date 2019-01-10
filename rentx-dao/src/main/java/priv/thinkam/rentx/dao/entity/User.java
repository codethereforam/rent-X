package priv.thinkam.rentx.dao.entity;

import priv.thinkam.rentx.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户表
 *
 * @author yanganyu
 * @date 2019-01-10
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


}