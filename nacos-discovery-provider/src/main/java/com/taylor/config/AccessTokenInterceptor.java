package com.taylor.config;

import com.taylor.oauth.service.BaseRedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AccessTokenInterceptor implements HandlerInterceptor {
    /**
     * redis
     */
    @Autowired
    private BaseRedisService baseRedisService;

    /**
     * 进入controller层之前拦截请求
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
            throws Exception {
        System.out.println("---------------------开始进入请求地址拦截----------------------------");
        //获取到accessToken
        String accessToken = httpServletRequest.getHeader("accessToken");
        // 判断accessToken是否空
        if (StringUtils.isEmpty(accessToken)) {
            // 返回错误消息
            resultError(" this is parameter accessToken null ", httpServletResponse);
            return false;
        }
        //从redis 中获取获取到accessToken
        String appKey = (String) baseRedisService.getString(accessToken);
        if (StringUtils.isEmpty(appKey)) {
            // accessToken 已经失效!
            resultError(" this is accessToken Invalid ", httpServletResponse);
            return false;
        }
        // 正常执行业务逻辑...
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {

    }

    /**
     * TODO    返回错误提示
     * @param errorMsg
     * @param httpServletResponse
     * @return void
     */
    public void resultError(String errorMsg, HttpServletResponse httpServletResponse) throws IOException {
        PrintWriter printWriter = httpServletResponse.getWriter();
        // setResultError为封装的返回信息，请自定义
        printWriter.write(errorMsg);
    }
}
