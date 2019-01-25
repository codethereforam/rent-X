package priv.thinkam.rentx.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus Config
 *
 * @author yanganyu
 * @date 2019/1/10 11:16
 */
@Configuration
@MapperScan("priv.thinkam.rentx.admin.dao.mapper")
public class MybatisPlusConfig {
}
