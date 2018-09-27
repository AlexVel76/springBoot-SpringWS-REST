package com.epam.htsa;

import com.epam.htsa.restconvertor.Ticket2PdfMessageConverter;
import com.epam.htsa.view.CustomContentNegotiationStrategy;
import com.epam.htsa.view.JsonViewResolver;
import com.epam.htsa.view.PdfViewResolver;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import javax.annotation.Resource;
import java.util.List;

@Configuration
public class RestConfig extends WebMvcConfigurationSupport {

    @Resource(name = "pdfTicketReportView")
    private View pdfTicketReportView;


    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(ticket2PdfMessageConverter());
        converters.add(jsonMessageConverter());
        super.configureMessageConverters(converters);
    }

    @Bean
    public MappingJackson2HttpMessageConverter jsonMessageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }

    @Bean
    AbstractHttpMessageConverter<Object> ticket2PdfMessageConverter() {
        return new Ticket2PdfMessageConverter();
    }

    @Bean
    public ViewResolver pdfViewResolver() {
        return new PdfViewResolver();
    }

    @Bean
    public ViewResolver jsonViewResolver() {
        return new JsonViewResolver();
    }


    @Override
    protected void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.mediaType("pdf", MediaType.APPLICATION_PDF);
        configurer.mediaType("json", MediaType.APPLICATION_JSON_UTF8);
        configurer.defaultContentType(MediaType.APPLICATION_JSON_UTF8);
        configurer.ignoreAcceptHeader(false);
        configurer.strategies(getContentNegotiationStrategies());

        super.configureContentNegotiation(configurer);
    }

    private List<ContentNegotiationStrategy> getContentNegotiationStrategies() {
        List<ContentNegotiationStrategy> result = Lists.newArrayList();
        result.add(new CustomContentNegotiationStrategy());

        return result;
    }

    @Override
    protected void configureViewResolvers(ViewResolverRegistry registry) {
        registry.enableContentNegotiation(pdfTicketReportView);
        registry.order(1);
        registry.viewResolver(pdfViewResolver());
        registry.viewResolver(jsonViewResolver());
        super.configureViewResolvers(registry);
    }


}
