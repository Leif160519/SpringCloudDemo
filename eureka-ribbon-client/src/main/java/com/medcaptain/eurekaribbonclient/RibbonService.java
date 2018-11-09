package com.medcaptain.eurekaribbonclient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RibbonService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    /**
     *hi方法启用了hystrix熔断器功能
     *fallbackMethod为处理回退(fallback)逻辑的方法
     *在熔断器打开的状态下，会执行fallback逻辑，fallback逻辑最好是返回一些静态字符串，不需要处理复杂逻辑，也不需要远程调度其他服务
     *如果一定要在fallback逻辑中调度其他服务，最好在远程调度其他服务时也加上熔断器
     */
    public String hi(String name) {
        return restTemplate.getForObject("http://eureka-client/hi?name=" + name, String.class);
    }

    public String hiError(String name) {
        return "hi," + name + ",sorry,error!";
    }
}
