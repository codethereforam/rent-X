package priv.thinkam.rentx.common.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fasterxml.jackson.core.JsonEncoding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.regex.Pattern;

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
	 * Configure View (url+'.json' view) resolver to provide JSON output using fastjson library to
	 * convert object in JSON format.
	 *
	 * @return org.springframework.web.servlet.ViewResolver
	 * @author yanganyu
	 * @date 2018/10/15 14:33
	 */
	@Bean
	public ViewResolver jsonViewResolver() {
		return (viewName, locale) -> new MappingFastjson2JsonView();
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

	/**
	 * 配置@RestController、@ResponseBody、@RequestBody使用fastjson
	 *
	 * @param converters converters
	 * @return void
	 * @author yanganyu
	 * @date 2018/10/15 16:06
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		converters.add(0, converter);
	}

	/**
	 * Spring MVC {@link View} that renders JSON content by serializing the model for the current request
	 * using fastjson.
	 *
	 * <p>By default, the entire contents of the model map (with the exception of framework-specific classes)
	 * will be encoded as JSON. If the model contains only one key, you can have it extracted encoded as JSON
	 * alone via  {@link #setExtractValueFromSingleKeyModel}.
	 *
	 * @author yanganyu
	 * @date 2018/10/15 15:48
	 */
	private static class MappingFastjson2JsonView extends AbstractView {
		/**
		 * Default content type: "application/json".
		 * Overridable through {@link #setContentType}.
		 */
		public static final String DEFAULT_CONTENT_TYPE = "application/json";

		private JsonEncoding encoding = JsonEncoding.UTF8;

		private boolean disableCaching = true;

		/**
		 * Default content type for JSONP: "application/javascript".
		 *
		 * @deprecated Will be removed as of Spring Framework 5.1, use
		 * <a href="https://docs.spring.io/spring/docs/5.0.x/spring-framework-reference/web.html#mvc-cors">CORS</a> instead.
		 */
		@Deprecated
		public static final String DEFAULT_JSONP_CONTENT_TYPE = "application/javascript";

		/**
		 * Pattern for validating jsonp callback parameter values.
		 */
		private static final Pattern CALLBACK_PARAM_PATTERN = Pattern.compile("[0-9A-Za-z_\\.]*");

		@Nullable
		private Set<String> modelKeys;

		private boolean extractValueFromSingleKeyModel = false;

		@Nullable
		private Set<String> jsonpParameterNames = new LinkedHashSet<>();

		public MappingFastjson2JsonView() {
			setContentType(DEFAULT_CONTENT_TYPE);
			setExposePathVariables(false);
		}

		/**
		 * Set the {@code JsonEncoding} for this view.
		 * By default, {@linkplain JsonEncoding#UTF8 UTF-8} is used.
		 */
		public void setEncoding(JsonEncoding encoding) {
			Assert.notNull(encoding, "'encoding' must not be null");
			this.encoding = encoding;
		}

		/**
		 * Return the {@code JsonEncoding} for this view.
		 */
		public final JsonEncoding getEncoding() {
			return this.encoding;
		}

		/**
		 * Disables caching of the generated JSON.
		 * <p>Default is {@code true}, which will prevent the client from caching the generated JSON.
		 */
		public void setDisableCaching(boolean disableCaching) {
			this.disableCaching = disableCaching;
		}

		/**
		 * Set whether to serialize models containing a single attribute as a map or
		 * whether to extract the single value from the model and serialize it directly.
		 * <p>The effect of setting this flag is similar to using
		 * {@code MappingJackson2HttpMessageConverter} with an {@code @ResponseBody}
		 * request-handling method.
		 * <p>Default is {@code false}.
		 */
		public void setExtractValueFromSingleKeyModel(boolean extractValueFromSingleKeyModel) {
			this.extractValueFromSingleKeyModel = extractValueFromSingleKeyModel;
		}

		@Override
		protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) {
			setResponseContentType(request, response);
			response.setCharacterEncoding(this.encoding.getJavaName());
			if (this.disableCaching) {
				response.addHeader("Pragma", "no-cache");
				response.addHeader("Cache-Control", "no-cache, no-store, max-age=0");
				response.addDateHeader("Expires", 1L);
			}
		}

		/**
		 * {@inheritDoc}
		 */
		public void setModelKey(String modelKey) {
			this.modelKeys = Collections.singleton(modelKey);
		}

		/**
		 * Set the attributes in the model that should be rendered by this view.
		 * When set, all other model attributes will be ignored.
		 */
		public void setModelKeys(@Nullable Set<String> modelKeys) {
			this.modelKeys = modelKeys;
		}

		/**
		 * Return the attributes in the model that should be rendered by this view.
		 */
		@Nullable
		public final Set<String> getModelKeys() {
			return this.modelKeys;
		}

		/**
		 * Set JSONP request parameter names. Each time a request has one of those
		 * parameters, the resulting JSON will be wrapped into a function named as
		 * specified by the JSONP request parameter value.
		 * <p>As of Spring Framework 5.0.7, there is no parameter name configured
		 * by default.
		 *
		 * @see <a href="http://en.wikipedia.org/wiki/JSONP">JSONP Wikipedia article</a>
		 * @since 4.1
		 * @deprecated Will be removed as of Spring Framework 5.1, use
		 * <a href="https://docs.spring.io/spring/docs/5.0.x/spring-framework-reference/web.html#mvc-cors">CORS</a> instead.
		 */
		@Deprecated
		public void setJsonpParameterNames(Set<String> jsonpParameterNames) {
			this.jsonpParameterNames = jsonpParameterNames;
		}

		@Nullable
		private String getJsonpParameterValue(HttpServletRequest request) {
			if (this.jsonpParameterNames != null) {
				for (String name : this.jsonpParameterNames) {
					String value = request.getParameter(name);
					if (StringUtils.isEmpty(value)) {
						continue;
					}
					if (!isValidJsonpQueryParam(value)) {
						if (logger.isDebugEnabled()) {
							logger.debug("Ignoring invalid jsonp parameter value: " + value);
						}
						continue;
					}
					return value;
				}
			}
			return null;
		}

		/**
		 * Validate the jsonp query parameter value. The default implementation
		 * returns true if it consists of digits, letters, or "_" and ".".
		 * Invalid parameter values are ignored.
		 *
		 * @param value the query param value, never {@code null}
		 * @since 4.1.8
		 * @deprecated Will be removed as of Spring Framework 5.1, use
		 * <a href="https://docs.spring.io/spring/docs/5.0.x/spring-framework-reference/web.html#mvc-cors">CORS</a> instead.
		 */
		@Deprecated
		protected boolean isValidJsonpQueryParam(String value) {
			return CALLBACK_PARAM_PATTERN.matcher(value).matches();
		}

		/**
		 * Filter out undesired attributes from the given model.
		 * The return value can be either another {@link Map} or a single value object.
		 * <p>The default implementation removes {@link BindingResult} instances and entries
		 * not included in the {@link #setModelKeys modelKeys} property.
		 *
		 * @param model the model, as passed on to {@link #renderMergedOutputModel}
		 * @return the value to be rendered
		 */
		protected Object filterModel(Map<String, Object> model) {
			Map<String, Object> result = new HashMap<>(model.size());
			Set<String> modelKeys = (!CollectionUtils.isEmpty(this.modelKeys) ? this.modelKeys : model.keySet());
			model.forEach((clazz, value) -> {
				if (!(value instanceof BindingResult) && modelKeys.contains(clazz)) {
					result.put(clazz, value);
				}
			});
			return (this.extractValueFromSingleKeyModel && result.size() == 1 ? result.values().iterator().next() : result);
		}

		@Override
		protected void setResponseContentType(HttpServletRequest request, HttpServletResponse response) {
			if (getJsonpParameterValue(request) != null) {
				response.setContentType(DEFAULT_JSONP_CONTENT_TYPE);
			} else {
				super.setResponseContentType(request, response);
			}
		}

		@Override
		protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
			Object value = filterModel(model);
			response.getWriter().write(JSONObject.toJSONString(value));
		}
	}
}
