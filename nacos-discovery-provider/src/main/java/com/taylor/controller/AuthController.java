package com.taylor.controller;

import com.alibaba.nacos.client.utils.JSONUtils;
import com.taylor.oauth.dao.entity.AppEntry;
import com.taylor.oauth.dao.mapper.AppEntryDao;
import com.taylor.oauth.service.BaseRedisService;
import com.taylor.oauth.service.TokenUtils;
import com.taylor.oauth.service.exception.ApiException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    /**
     * Redis
     */
    @Autowired
    private BaseRedisService baseRedisService;

    /**
     * 创建的表appEntry ,的 dao对象
     */
    @Autowired
    private AppEntryDao appMapper;

    /**
     * 过期时间，单位秒
     */
    private long timeToken = 60 * 60 * 2;


    /**
     * TODO    使用appKey+appSecret 生成AccessToke
     * @param appEntry
     * @return java.lang.String
     */
    @RequestMapping("/refreshAccessToken")
    public String refreshAccessToken(@RequestBody AppEntry appEntry) throws IOException {
        // 使用appId + appSecret查询
        AppEntry appResult = appMapper.findApp(appEntry);
        // 判断是否存在商户信息，等同与微信开发平台申请的appid，appSecret信息是否正确
        if (appResult == null) {
            throw new ApiException("没有对应机构的认证信息");
        }
        //判断是否开发权限给该商户
        if ("1".equals(appResult.getDisabled())) {
            throw new ApiException("您现在没有权限生成对应的AccessToken");
        }
        // 从redis中删除之前的accessToken
        if (StringUtils.isNoneBlank(appResult.getAccessToken()))
        {
            baseRedisService.delKey(appResult.getAccessToken());
        }
        // 生成的新的accessToken 保存到 Redis，并保存到数据库
        String newAccessToken = newAccessToken(appResult.getAppKey());
        //返回 accessToken，setResultSuccessData为封装返回信息，请自定义
        Map<String, String> result = new HashMap<>();
        result.put("accessToken", newAccessToken);
        return JSONUtils.serializeObject(result);
    }


    /**
     * TODO
     * @param appKey
     * @return java.lang.String
     */
    private String newAccessToken(String appKey) {
        // 使用 appKey+appSecret 生成对应的AccessToken, 保存两个小时
        String accessToken = TokenUtils.getAccessToken();
        // 保证在同一个事物redis 事物中
        // 生成最新的token, key=accessToken ,value=appKey
        baseRedisService.setString(accessToken, appKey, timeToken);
        // 表数据更新为最新的 accessToken，删除之前的accessToken使用
        appMapper.updateAccessToken(accessToken, appKey);
        return accessToken;
    }
}
