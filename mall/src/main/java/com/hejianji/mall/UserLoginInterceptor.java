package com.hejianji.mall;

import com.hejianji.mall.consts.MallConst;
import com.hejianji.mall.enums.ResponceEnum;
import com.hejianji.mall.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class UserLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle............");
        User user=(User)request.getSession().getAttribute(MallConst.CURRENT_USER);
        if(user==null){
            //如果用户数据为空，则停止
            return false;
        }
        return true;
    }
}
