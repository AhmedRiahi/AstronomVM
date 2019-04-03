package com.astronomvm.component;

import org.springframework.web.client.RestTemplate;

public class RestRequester {

    private RestTemplate restTemplate = new RestTemplate();


    public Object sendRequest(String url){
        return this.restTemplate.getForObject(url,Object.class);
    }
}
