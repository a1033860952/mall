package com.hejianji.mall.controller;

import com.hejianji.mall.enums.ResponceEnum;
import com.hejianji.mall.from.UserForm;
import com.hejianji.mall.pojo.User;
import com.hejianji.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Objects;

import static com.hejianji.mall.enums.ResponceEnum.CART_PRODUCT_NOT_EXIST;
import static com.hejianji.mall.enums.ResponceEnum.PARAM_ERROR;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @PostMapping("/register")
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
//        log.info("username={}",user.getUsername());
        return ResponseVo.success("注册成功");
//        return ResponseVo.error(CART_PRODUCT_NOT_EXIST);

    }

}
