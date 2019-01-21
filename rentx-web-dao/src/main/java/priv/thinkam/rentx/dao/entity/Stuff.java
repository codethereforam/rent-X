package priv.thinkam.rentx.dao.entity;

import java.math.BigDecimal;
import priv.thinkam.rentx.common.base.BaseEntity;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 物品
 *
 * @author yanganyu
 * @date 2019-01-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ToString(callSuper = true)
public class Stuff extends BaseEntity {

    /**
     * 类别编号
     */
    private Integer categoryId;

    /**
     * 物品名称
     */
    private String name;

    /**
     * 押金(rmb)
     */
    private BigDecimal deposit;

    /**
     * 租金（rmb/day）
     */
    private BigDecimal rental;

    /**
     * 物品状态（0:未租；1:申请租用；2:已租）
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


}
