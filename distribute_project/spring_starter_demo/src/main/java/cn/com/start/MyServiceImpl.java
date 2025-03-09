package cn.com.start;

/**
 * @Author hongjian.li
 * @Description
 * @Date 3/4/2025 9:46 PM
 **/
public class MyServiceImpl implements MyService {

    private final MyProperties myProperties;

    public MyServiceImpl(MyProperties myProperties) {
        this.myProperties = myProperties;
    }

    @Override
    public String getName() {
        return myProperties.getName();
    }
}
