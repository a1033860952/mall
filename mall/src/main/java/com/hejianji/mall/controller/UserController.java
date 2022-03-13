package com.hejianji.mall.controller;

import com.hejianji.mall.consts.MallConst;
import com.hejianji.mall.enums.ResponceEnum;
import com.hejianji.mall.from.UserForm;
import com.hejianji.mall.from.UserLoginForm;
import com.hejianji.mall.pojo.User;
import com.hejianji.mall.service.impl.UserServiceImpl;
import com.hejianji.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.Objects;

import static com.hejianji.mall.enums.ResponceEnum.CART_PRODUCT_NOT_EXIST;
import static com.hejianji.mall.enums.ResponceEnum.PARAM_ERROR;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/user/register")
    //@Valid是用来表单校验
    //当方法参数为User时，且request是采用json传值的话，必须使用@RequestBody User user  才能正常接收到到
    public ResponseVo register(@Valid @RequestBody UserForm userForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("注册提交的参数有误，{} {}",
                    Objects.requireNonNull(bindingResult.getFieldError()).getField(),
                    bindingResult.getFieldError().getDefaultMessage());
            //返回参数错误，PARAM_ERROR    bindingResult是告诉是哪个参数的
            return ResponseVo.error(PARAM_ERROR,bindingResult);

        }

        User user=new User();
        BeanUtils.copyProperties(userForm, user);
        return userService.register(user);




    }


    @PostMapping("/user/login")
    public ResponseVo login(@Valid @RequestBody UserLoginForm userLoginForm, BindingResult bindingResult , HttpSession session){
        if (bindingResult.hasErrors()){
            //返回参数错误，PARAM_ERROR    bindingResult是告诉是哪个参数的
            return ResponseVo.error(PARAM_ERROR,bindingResult);
        }
        ResponseVo<User> userResponseVo = userService.login(userLoginForm.getUsername(), userLoginForm
                .getPassword());

        //设置session
        session.setAttribute(MallConst.CURRENT_USER,userResponseVo.getData());


        return userResponseVo;

    }

    /**
     * 获取登录信息
     * @param session
     * @return
     */
    @GetMapping("/user")
    public ResponseVo<User> userInfo(HttpSession session){
        //因为在login方法中，就将User中的数据放到session中了，所以这里强制转换成User是可以的
        User user=(User)session.getAttribute(MallConst.CURRENT_USER);
        log.info("进入userinfo方法了");
        return ResponseVo.success(user);
    }


    @PostMapping("/user/logout")
    public ResponseVo logout(HttpSession session){
        session.removeAttribute(MallConst.CURRENT_USER);
        return ResponseVo.successByMsg();
    }



}
