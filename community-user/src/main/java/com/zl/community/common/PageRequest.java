package com.zl.community.common;


import lombok.Data;

import static com.zl.community.constant.Constants.CommonConstant.SORT_ORDER_ASC;

/**
 * @Author : ZL
 * 分页请求
 */
@Data
public class PageRequest {

    /**
     * 当前页号
     */
    private long current = 1;

    /**
     * 页面大小
     */
    private long pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认升序）
     */
    private String sortOrder = SORT_ORDER_ASC;
}
