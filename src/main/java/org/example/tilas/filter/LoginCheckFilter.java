package org.example.tilas.filter;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Request;
import org.example.tilas.pojo.Result;
import org.example.tilas.utils.JwtUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1、获取请求url
        String url = request.getRequestURL().toString();
        log.info("请求的URL: {}",url);
        //判断请求url是否包含login，如果包含，说明是登录操作，放行
        if(url.contains("login")){
            log.info("登录操作");
            filterChain.doFilter(request, response);
            return;
        }
        //获取请求头中的令牌（token）
        String jwt = request.getHeader("token");
        //判断令牌是否存在，如果不存在，返回错误结果
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空，返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换 对象json
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return;
        }
        //解析token，如果解析失败，返回错误结果
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            //jwt解析失败
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录的错误信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换 对象json
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return;
        }

        //放行
        log.info("令牌合法，放行");
        filterChain.doFilter(request, response);
    }
}
