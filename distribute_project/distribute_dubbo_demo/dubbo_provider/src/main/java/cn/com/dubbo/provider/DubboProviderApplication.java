package cn.com.dubbo.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author hongjian.li
 * @Description
 * @Date 2/20/2025 8:20 PM
 **/
@SpringBootApplication
@EnableDubbo(scanBasePackages = {"cn.com.dubbo.provider.service"})
public class DubboProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
    }
}
