package priv.thinkam.rentx.web.service.param;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 租用项日统计 param
 *
 * @author yanganyu
 * @date 2019/3/16 10:48
 */
@Data
@Accessors(chain = true)
public class ItemDailyStatsParam {
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