package priv.thinkam.rentx.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 配置多视图解析，包括thymeleaf和Json两种视图
 * <ul>
 * <li>通过url查看thymeleaf视图</li>
 * <li>通过url+'.json'查看Json视图</li>
 * </ul>
 *
 * @author yanganyu
 * @date 2018/10/15 14:16
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.ignoreAcceptHeader(true).defaultContentType(MediaType.TEXT_HTML);
	}

	/**
	 * 映射静态资源路径
	 *
	 * @param registry registry
	 * @author yanganyu
	 * @date 2018/10/15 14:30
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//相当于 <mvc:resources mapping="/**" location="/static/" /> 也可设置多个路径
		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/static/");
	}


	/**
	 * Configure ContentNegotiatingViewResolver
	 *
	 * @param manager manager
	 * @return org.springframework.web.servlet.ViewResolver
	 * @author yanganyu
	 * @date 2018/10/15 14:31
	 */
	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);
		// 定义所有的视图解析器
		List<ViewResolver> resolvers = new ArrayList<>();
		resolvers.add(jsonViewResolver());
		resolvers.add(thymeleafViewResolver());
		resolver.setViewResolvers(resolvers);
		return resolver;
	}

	/**
	 * Configure View (url+'.json' view) resolver to provide JSON output using jackson library to
	 * convert object in JSON format.
	 *
	 * @return org.springframework.web.servlet.ViewResolver
	 * @author yanganyu
	 * @date 2018/10/15 14:33
	 */
	@Bean
	public ViewResolver jsonViewResolver() {
		MappingJackson2JsonView mappingJackson2JsonView = new MappingJackson2JsonView();
		// 设置LocalDateTime等序列化
		mappingJackson2JsonView.setObjectMapper(objectMapper());
		return (viewName, locale) -> mappingJackson2JsonView;
	}

	/**
	 * 配置Thymeleaf视图解析器
	 *
	 * @return org.thymeleaf.spring5.view.ThymeleafViewResolver
	 * @author yanganyu
	 * @date 2018/10/15 14:33
	 */
	private ThymeleafViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		// 注意“order”和“viewNames”是可选的
		viewResolver.setOrder(1);
		viewResolver.setViewNames(new String[]{".html"});
		return viewResolver;
	}

	/**
	 * 创建模版引擎并为模板引擎注入模板解析器
	 *
	 * @return org.thymeleaf.spring5.SpringTemplateEngine
	 * @author yanganyu
	 * @date 2018/10/15 14:33
	 */
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.setEnableSpringELCompiler(true);
		return templateEngine;
	}

	/**
	 * 创建模版解析器
	 *
	 * @return org.thymeleaf.templateresolver.ITemplateResolver
	 * @author yanganyu
	 * @date 2018/10/15 14:34
	 */
	@Bean
	public ITemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setPrefix("classpath:/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCacheable(false);
		return templateResolver;
	}

	private ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addSerializer(LocalDateTime.class,
				new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		objectMapper.registerModule(javaTimeModule);
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		return objectMapper;
	}

	private MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		// 由于将response header中Content-Type默认设置为text/html，会造成默认jackson无法解析，参考fastjson源码
		jsonConverter.setSupportedMediaTypes(Collections.singletonList((MediaType.ALL)));
		// 设置LocalDateTime等序列化
		jsonConverter.setObjectMapper(objectMapper());
		return jsonConverter;
	}

	/**
	 * 配置@RestController、@ResponseBody、@RequestBody使用自定义json converter
	 *
	 * @param converters converters
	 * @author yanganyu
	 * @date 2018/10/15 16:06
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(0, customJackson2HttpMessageConverter());
	}
}
