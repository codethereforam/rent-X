package priv.thinkam.rentx.web.dao.entity;

import priv.thinkam.rentx.common.base.BaseEntity;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 角色
 *
 * @author yanganyu
 * @date 2019-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ToString(callSuper = true)
public class Role extends BaseEntity {

    /**
     * 角色标识符
     */
    private String identifier;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色描述
     */
    private String description;


}
