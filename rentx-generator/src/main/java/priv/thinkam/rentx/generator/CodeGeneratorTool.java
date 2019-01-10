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
		// 代码生成器
		AutoGenerator autoGenerator = new AutoGenerator();
		// 全局配置
		GlobalConfig globalConfig = new GlobalConfig();
		globalConfig.setOutputDir(StringUtil.format("{}/generate/rent-X", System.getProperty("user.home")));
		globalConfig.setAuthor("yanganyu");
		globalConfig.setFileOverride(true);
		globalConfig.setServiceImplName("%sService");
		globalConfig.setBaseResultMap(true);
		globalConfig.setBaseColumnList(true);
		autoGenerator.setGlobalConfig(globalConfig);

		// 数据源配置
		DataSourceConfig dataSourceConfig = new DataSourceConfig();
		dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/rentx?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8");
		dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
		dataSourceConfig.setUsername("root");
		dataSourceConfig.setPassword("root");
		autoGenerator.setDataSource(dataSourceConfig);

		// 包配置
		PackageConfig packageConfig = new PackageConfig();
		packageConfig.setParent("priv.thinkam.rentx");
		packageConfig.setEntity("dao.entity");
		packageConfig.setMapper("dao.mapper");
		packageConfig.setXml("dao.mapper");
		packageConfig.setServiceImpl("service");
		packageConfig.setController("web.controller");
		autoGenerator.setPackageInfo(packageConfig);

		/// 自定义配置
		// InjectionConfig injectionConfig = new InjectionConfig() {
		// 	@Override
		// 	public void initMap() {
		// 	}
		// };
		// autoGenerator.setCfg(injectionConfig);

		// 配置模板
		TemplateConfig templateConfig = new TemplateConfig();
		// 不生成service接口
		templateConfig.setService(null);
		autoGenerator.setTemplate(templateConfig);

		// 策略配置
		StrategyConfig strategyConfig = new StrategyConfig();
		strategyConfig.setNaming(NamingStrategy.underline_to_camel);
		strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
		strategyConfig.setSuperEntityClass("priv.thinkam.rentx.common.base.BaseEntity");
		strategyConfig.setSuperEntityColumns("id", "add_user_id", "add_time", "update_user_id", "update_time", "mark");
		strategyConfig.setEntityLombokModel(true);
		strategyConfig.setSuperControllerClass("priv.thinkam.rentx.common.base.BaseController");
		/// 包括哪几张表
		// strategyConfig.setInclude("aa", "bb");
		/// 不包括哪几张表
		// strategyConfig.setExclude("cc", "dd");
		autoGenerator.setStrategy(strategyConfig);

		autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
		autoGenerator.execute();
	}
}
