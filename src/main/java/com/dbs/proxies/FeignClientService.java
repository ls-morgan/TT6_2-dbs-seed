package com.dbs.proxies;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "FeignClient1", url = "${feign.client.proxy.url}", configuration = FeignClientConfig.class)
public interface FeignClientService {

}
