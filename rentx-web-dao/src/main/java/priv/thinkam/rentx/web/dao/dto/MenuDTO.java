package priv.thinkam.rentx.web.dao.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 菜单DTO
 *
 * @author yanganyu
 * @date 2019/3/16 20:05
 */
@Data
@Accessors(chain = true)
public class MenuDTO {
	/**
	 * 菜单ID
	 */
	private Integer id;
	/**
	 * 父ID，根菜单pid为0
	 */
	private Integer pid;
	/**
	 * 菜单名称
	 */
	private String name;
	/**
	 * 菜单URL
	 */
	private String url;
}
