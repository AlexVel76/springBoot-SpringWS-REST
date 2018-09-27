package com.epam.htsa;

import com.epam.htsa.exception.HomeTaskExceptioin;
import com.epam.htsa.storage.StorageProperties;
import com.epam.htsa.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
@ComponentScan
@EnableConfigurationProperties(StorageProperties.class)
public class SpringBootApplicationConfig implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationConfig.class, args);
    }

    @SuppressWarnings("deprecation")
    @Bean
    public CastorMarshaller castorMarshaller(){
        return new CastorMarshaller();
    }

    @Bean
    public SimpleMappingExceptionResolver exceptionResolver(){
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties exceptions = new Properties();
        exceptions.put(HomeTaskExceptioin.class, "error");
        exceptions.put(RuntimeException.class, "error");
        resolver.setExceptionMappings(exceptions);
        return resolver;
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }


}
