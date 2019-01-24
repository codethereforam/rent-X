package priv.thinkam.rentx.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import priv.thinkam.rentx.common.util.StringUtil;

/**
 * 代码生成器
 *
 * @author thinkam
 * @date 2019/01/01
 */
public class CodeGeneratorTool {

	public static void main(String[] args) {
		// 全局配置
		GlobalConfig globalConfig = new GlobalConfig();
		globalConfig.setOutputDir(StringUtil.format("{}/generate/rent-X", System.getProperty("user.home")))
				.setAuthor("yanganyu")
				.setFileOverride(true)
				.setServiceImplName("%sService")
				.setBaseResultMap(true)
				.setBaseColumnList(true);

		// 数据源配置
		DataSourceConfig dataSourceConfig = new DataSourceConfig();
		dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/rentx?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8")
				.setDriverName("com.mysql.cj.jdbc.Driver")
				.setUsername("root")
				.setPassword(System.getenv("MYSQL_PASSWD"));

		// 包配置
		PackageConfig packageConfig = new PackageConfig();
		packageConfig.setParent("priv.thinkam.rentx.web")
				.setEntity("dao.entity")
				.setMapper("dao.mapper")
				.setXml("dao.mapper.sqlmap")
				.setServiceImpl("service")
				.setController("controller");

		// 配置模板
		TemplateConfig templateConfig = new TemplateConfig();
		// 不生成service接口
		templateConfig.setService(null);

		// 策略配置
		StrategyConfig strategyConfig = new StrategyConfig();
		strategyConfig.setNaming(NamingStrategy.underline_to_camel)
				.setColumnNaming(NamingStrategy.underline_to_camel)
				.setSuperEntityClass("priv.thinkam.rentx.common.base.BaseEntity")
				.setSuperEntityColumns("id", "add_user_id", "add_time", "update_user_id", "update_time", "mark")
				.setEntityLombokModel(true)
				/// 包括哪几张表
				//.setInclude("aa", "bb")
				/// 不包括哪几张表
				//.setExclude("cc", "dd")
				.setSuperControllerClass("priv.thinkam.rentx.common.base.BaseController");

		new AutoGenerator().setGlobalConfig(globalConfig)
				.setDataSource(dataSourceConfig)
				.setPackageInfo(packageConfig)
				.setTemplate(templateConfig)
				.setStrategy(strategyConfig)
				.setTemplateEngine(new FreemarkerTemplateEngine())
				.execute();
	}
}
