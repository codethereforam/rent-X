package priv.thinkam.rentx.web.dao.entity.es;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.math.BigDecimal;
import java.util.Date;

/**
 * stuff document (map to es)
 *
 * @author yanganyu
 * @date 2019-01-19
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@Document(indexName = "rentx", type = "stuff")
public class StuffDocument {
	/**
	 * ID
	 */
	@Id
	private Integer id;

	/**
	 * 类别编号
	 */
	private Integer categoryId;

	/**
	 * 物品名称
	 */
	private String name;

	/**
	 * 物品描述
	 */
	private String description;

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
	 * 图片id
	 */
	private String pictureId;

	/**
	 * 物品所有者ID
	 */
	private Integer userId;

	/**
	 * 添加人ID
	 */
	private Integer addUserId;

	/**
	 * 添加时间
	 */
	@Field(format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	private Date addTime;

	/**
	 * 更新人ID
	 */
	private Integer updateUserId;

	/**
	 * 修改时间
	 */
	@Field(format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	private Date updateTime;

	/**
	 * 标识(是否有效 0有效；-1无效)
	 */
	private Boolean mark;
}
