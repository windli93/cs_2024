package cn.com.dubbo.provider.service.impl;

import cn.com.dubbo.api.DemoService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "0.0.1")
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}