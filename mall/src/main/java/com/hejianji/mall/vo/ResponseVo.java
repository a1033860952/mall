package com.hejianji.mall.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hejianji.mall.enums.ResponceEnum;
import lombok.Data;
import org.springframework.validation.BindingResult;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseVo<T> {
    private Integer status;
    private String msg;
    private T data;



    public ResponseVo(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public static <T> ResponseVo<T> success(String msg){
        return new ResponseVo<>(ResponceEnum.SUCCESS.getCode(),
                ResponceEnum.SUCCESS.getDesc());

    }

    public static <T> ResponseVo<T> error(ResponceEnum responceEnum){
        return new ResponseVo<>(responceEnum.getCode(), responceEnum.getDesc());

    }

    public static <T> ResponseVo<T> error(ResponceEnum responceEnum,String msg){
        return new ResponseVo<>(responceEnum.getCode(), msg );

    }
    public static <T> ResponseVo<T> error(ResponceEnum responceEnum, BindingResult bindingResult){
        return new ResponseVo<>(responceEnum.getCode(), bindingResult.getFieldError().getField()+ "  "+bindingResult.getFieldError().getDefaultMessage());

    }
}
