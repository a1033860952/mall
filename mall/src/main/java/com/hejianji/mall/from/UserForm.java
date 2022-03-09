package com.hejianji.mall.from;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserForm {

    /**
     * @NotBlank 不能为空  用于String 判断空格
     * @BotNull  不能为null
     * @NotEmppty 用于集合
     */
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;

}
