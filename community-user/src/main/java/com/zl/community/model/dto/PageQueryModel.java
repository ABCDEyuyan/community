package com.zl.community.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author : ZL
 */
@Data
public class PageQueryModel implements Serializable {
    private Integer pageNo;
    private Integer pageSize;
}
