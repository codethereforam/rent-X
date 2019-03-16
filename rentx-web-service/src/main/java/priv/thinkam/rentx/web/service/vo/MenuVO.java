package priv.thinkam.rentx.web.service.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单VO
 * <p>
 * （该类数据由管理员，不考虑XSS防御，因此不必继承BaseVO）
 * <p/>
 */
@Data
@Accessors(chain = true)
public class MenuVO implements Serializable {

	/**
	 * 子菜单
	 */
	private List<MenuVO> children;
	/**
	 * 菜单名称
	 */
	private String name;
	/**
	 * 菜单URL
	 */
	private String url;
}
