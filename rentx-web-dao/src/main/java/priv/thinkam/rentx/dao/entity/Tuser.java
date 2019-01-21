package priv.thinkam.rentx.dao.entity;

import lombok.ToString;
import priv.thinkam.rentx.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 测试用户表
 *
 * @author yanganyu
 * @date 2019-01-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ToString(callSuper = true)
public class Tuser extends BaseEntity {

    /**
     * 名称
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;


}
