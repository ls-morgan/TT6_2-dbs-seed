package com.dbs.proxies;

import feign.Client;
import org.springframework.context.annotation.Bean;

import javax.net.ssl.HttpsURLConnection;

public class FeignClientConfig {

    public FeignClientConfig() {
    }

    @Bean
    public Client client() {
        return new CustomFeignClient(HttpsURLConnection.getDefaultSSLSocketFactory(), HttpsURLConnection.getDefaultHostnameVerifier());
    }
}
