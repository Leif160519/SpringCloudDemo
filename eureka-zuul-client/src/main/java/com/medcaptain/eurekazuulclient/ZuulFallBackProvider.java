package com.medcaptain.eurekazuulclient;

import org.springframework.http.client.ClientHttpResponse;

/**
 * 实现该接口的两个方法
 * 1.getRoute()方法，指定熔断功能应用于哪些路由的服务
 * 2.fallbackResponse()为进入熔断功能时执行的逻辑
 *
 * @return
 */
public interface ZuulFallBackProvider {
    //指定熔断功能应用于哪些路由的服务
    public String getRoute();

    //为进入熔断功能时执行的逻辑
    public ClientHttpResponse fallbackResponse();
}
