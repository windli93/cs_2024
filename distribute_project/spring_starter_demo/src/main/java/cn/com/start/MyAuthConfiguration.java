package cn.com.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author hongjian.li
 * @Description
 * @Date 3/4/2025 9:41 PM
 **/
@Configuration
@EnableConfigurationProperties(MyProperties.class)
public class MyAuthConfiguration {

    @Autowired
    private MyProperties properties;

    @Bean
    public MyService myService(){
        return new MyServiceImpl(properties);
    }

}
