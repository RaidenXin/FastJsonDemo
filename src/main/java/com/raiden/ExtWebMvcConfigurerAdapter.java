package com.raiden;

import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.raiden.filter.DataToStringFilter;
import com.raiden.filter.FirstLetterCapitalizedFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import java.util.List;

@Configuration
public class ExtWebMvcConfigurerAdapter extends WebMvcConfigurationSupport {

    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //new一个自定义的转换器
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonMessageConverter();
        //过滤器链
        SerializeFilter[] filters = {new FirstLetterCapitalizedFilter(), new DataToStringFilter()};
        //将过滤器链放入自定义转换器中
        fastJsonHttpMessageConverter.getFastJsonConfig().setSerializeFilters(filters);
        //将转换器放入转换器链中
        converters.add(fastJsonHttpMessageConverter);
        //将转换器链放入配置管理器中
        super.configureMessageConverters(converters);
    }
}
