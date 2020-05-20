package com.taylor.service;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class BizService {

    @Autowired
    RestTemplate restTemplate;

    @GlobalTransactional
    @Transactional
    public void biz() {
        restTemplate.getForObject("http://localhost:8020/order", String.class);
        restTemplate.getForObject("http://localhost:8030/pay", String.class);
//        int i = 1/0;
    }

}
