package priv.thinkam.rentx.web.dao.entity;

import priv.thinkam.rentx.common.base.BaseEntity;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 资源
 *
 * @author yanganyu
 * @date 2019-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ToString(callSuper = true)
public class Resource extends BaseEntity {

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源描述
     */
    private String description;

    /**
     * 资源URL
     */
    private String url;

    /**
     * HTTP方法
     */
    private String method;


}
