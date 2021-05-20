package com.example.demospringsecurity.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Verification;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@Component
public class Interceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        boolean flag = false;
        String token = request.getHeader("token");

        if(token == null || StringUtils.isBlank(token)){
            response.getWriter().write("token is empty,please login again!");
            return flag;
        }

        try{
            token = request.getHeader("token");
            Verification verification = JWT.require(Algorithm.HMAC256("secret"));
            verification.build().verify(token);
            flag = true;
        }catch (RuntimeException e){
            e.printStackTrace();
            response.getWriter().write("token verify error,please login again!");
        }

        JSONObject payload = JSON.parseObject(new String(Base64Utils.decodeFromUrlSafeString(JWT.decode(token).getPayload()), StandardCharsets.UTF_8));
        if(payload.getLongValue("expire") < System.currentTimeMillis()){
            response.getWriter().write("token is expired,please login again!");
            return false;
        }

        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }

}
