package com.zl.community.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author : ZL
 * 通用返回类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BaseResponse<T> implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer code;
    private String message;
    private T data;

}
