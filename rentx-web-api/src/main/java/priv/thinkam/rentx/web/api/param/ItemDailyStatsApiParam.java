package priv.thinkam.rentx.web.api.param;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 租用项日统计 api param
 *
 * @author yanganyu
 * @date 2019/3/16 10:47
 */
@Data
@Accessors(chain = true)
public class ItemDailyStatsApiParam implements Serializable {
	/**
	 * 开始创建日期
	 */
	private String beginAddDate;
	/**
	 * 结束创建日期
	 */
	private String endAddDate;
	/**
	 * 类别ID
	 */
	private Integer categoryId;
	/**
	 * 状态
	 */
	private Integer status;
}
