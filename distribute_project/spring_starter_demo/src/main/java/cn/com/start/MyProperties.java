package cn.com.start;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author hongjian.li
 * @Description
 * @Date 3/4/2025 9:42 PM
 **/
@ConfigurationProperties(prefix = "my")
public class MyProperties {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
