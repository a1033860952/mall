package com.hejianji.mall.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hejianji.mall.enums.ResponceEnum;
import lombok.Data;
import org.springframework.validation.BindingResult;

//  vo是视图层,其作用是将指定页面的展示数据封装起来,通常用于业务层之间的数据传递。”
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

    public ResponseVo(Integer status, T data) {
        this.status = status;
        this.data = data;
    }

    public static <T> ResponseVo<T> successByMsg(){
        return new ResponseVo<>(ResponceEnum.SUCCESS.getCode(),
                ResponceEnum.SUCCESS.getDesc());

    }

    public static <T> ResponseVo<T> success(T data){
        return new ResponseVo<>(ResponceEnum.SUCCESS.getCode(),
                data);
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
