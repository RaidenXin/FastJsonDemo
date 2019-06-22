package com.raiden;

import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.raiden.filter.DataToStringFilter;
import com.raiden.filter.FirstLetterCapitalizedFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class ExtWebMvcConfigurerAdapter extends WebMvcConfigurationSupport {

    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonMessageConverter();
        SerializeFilter[] filters = {new FirstLetterCapitalizedFilter(), new DataToStringFilter()};
        fastJsonHttpMessageConverter.getFastJsonConfig().setSerializeFilters(filters);
        converters.add(fastJsonHttpMessageConverter);
        super.configureMessageConverters(converters);
    }
}
