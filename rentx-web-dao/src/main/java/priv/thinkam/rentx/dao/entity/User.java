package priv.thinkam.rentx.dao.entity;

import priv.thinkam.rentx.common.base.BaseEntity;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 用户表
 *
 * @author yanganyu
 * @date 2019-01-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ToString(callSuper = true)
public class User extends BaseEntity {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别(0:女，1:男，2:不愿透露)
     */
    private Integer sex;

    /**
     * 状态(1:正常,0:锁定)
     */
    private Boolean status;


}
