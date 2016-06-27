package com.namee.core.spring.configuration;

import com.namee.core.common.message.NosMessageSource;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by namee on 2016. 6. 16..
 */
@Configuration
public class PropertiesConfiguration {
    private static final String VIEW_MESSAGE_PROPERTIES = "classpath:/message/message-common";
    private static final String SYSTEM_MESSAGE_PROPERTIES = "classpath:/message/message-system";
    private static final String APPLICATION_LOCAL_PROPERTIES = "/application.yml";
    private static final String DEFAULT_ENCODING = "UTF-8";

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        NosMessageSource messageSource = new NosMessageSource();
        messageSource.setBasenames(getBaseNames());
        messageSource.setDefaultEncoding(DEFAULT_ENCODING);
        messageSource.setCacheSeconds(86400);
        //1일	, 86400
        //1시간 , 3600
        return messageSource;
    }

    private String[] getBaseNames() {
        return new String[] { VIEW_MESSAGE_PROPERTIES, SYSTEM_MESSAGE_PROPERTIES };
    }

    @Bean
    public MessageSourceAccessor messageSourceAccessor() {
        return new MessageSourceAccessor(messageSource());
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("application.yml"));
        propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
        return propertySourcesPlaceholderConfigurer;
    }
//    @Bean
//    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
//        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
//        ppc.setLocations(new Resource[] { new ClassPathResource(APPLICATION_LOCAL_PROPERTIES) });
//        return ppc;
//    }
}
