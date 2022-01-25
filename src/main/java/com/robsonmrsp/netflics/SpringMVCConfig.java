package com.robsonmrsp.netflics;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.robsonmrsp.netflics.core.persistence.CatchThrowConstraintViolationException;
import com.robsonmrsp.netflics.core.rs.exception.SimpleErrorMessageHandlerExceptionResolver;
import com.robsonmrsp.netflics.core.serialization.CustomDoubleDeserializer;
import com.robsonmrsp.netflics.core.serialization.CustomLocalDateDeserializer;
import com.robsonmrsp.netflics.core.serialization.CustomLocalDateSerializer;
import com.robsonmrsp.netflics.core.serialization.CustomLocalDateTimeDeserializer;
import com.robsonmrsp.netflics.core.serialization.CustomLocalDateTimeSerializer;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class SpringMVCConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/login.html").addResourceLocations("/login.html");
        registry.addResourceHandler("/SpecRunner.html").addResourceLocations("/SpecRunner.html");
        registry.addResourceHandler("/vendor/**").addResourceLocations("/vendor/");
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/c/**").addResourceLocations("/c/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/decorators/**").addResourceLocations("/decorators/");
        registry.addResourceHandler("/downloads/**").addResourceLocations("/downloads/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
        registry.addResourceHandler("/sounds/**").addResourceLocations("/sounds/");
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/javascript/**").addResourceLocations("/javascript/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/j/**").addResourceLocations("/j/");
        registry.addResourceHandler("/uploads/**").addResourceLocations("/uploads/");
    }
    
	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	// https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.4-Release-Notes#default-servlet-registration
    @Bean
    WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> enableDefaultServlet() {
        return (factory) -> factory.setRegisterDefaultServlet(true);
    }
    
	
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new SimpleErrorMessageHandlerExceptionResolver());
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver createMultipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxInMemorySize(20971520);
        resolver.setMaxInMemorySize(1048576);

        return resolver;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() //
                .indentOutput(true) //

                .deserializerByType(LocalDate.class, new CustomLocalDateDeserializer())//
                .deserializerByType(LocalDateTime.class, new CustomLocalDateTimeDeserializer())//
                .deserializerByType(Double.class, new CustomDoubleDeserializer())//
                //
                .serializerByType(LocalDate.class, new CustomLocalDateSerializer())//
                .serializerByType(LocalDateTime.class, new CustomLocalDateTimeSerializer())//
        ;//
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
        converters.add(new MappingJackson2XmlHttpMessageConverter(Jackson2ObjectMapperBuilder.xml().build()));
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean(name = "afterThrow")
    public CatchThrowConstraintViolationException afterThrow() {
        return new CatchThrowConstraintViolationException();
    }

    @Bean
    public BeanNameAutoProxyCreator autoProxyCreator() {
        BeanNameAutoProxyCreator autoProxyCreator = new BeanNameAutoProxyCreator();
        autoProxyCreator.setProxyTargetClass(true);
        autoProxyCreator.setBeanNames(new String[] { "*Service", "*ServiceImp" });
        autoProxyCreator.setInterceptorNames("afterThrow");

        return autoProxyCreator;
    }
}