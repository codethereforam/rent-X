package priv.thinkam.rentx.dao.entity;

import priv.thinkam.rentx.common.base.BaseEntity;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 类别
 *
 * @author yanganyu
 * @date 2019-01-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ToString(callSuper = true)
public class Category extends BaseEntity {

    /**
     * 类别名称
     */
    private String name;

    /**
     * 类别描述
     */
    private String description;


}
