package com.zl.community.controller.user;

import com.zl.community.annotation.LogOperation;
import com.zl.community.common.BaseResponse;
import com.zl.community.common.ResultCode;
import com.zl.community.model.entity.TagEntity;
import com.zl.community.model.vo.TagVO;
import com.zl.community.service.TagService;
import com.zl.community.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author : ZL
 * 标签接口
 */
@RestController
@RequestMapping("/user/tag")
@Slf4j
@Api(tags = "标签接口")
@RequiredArgsConstructor
public class TagController {
    private final TagService service;

    @LogOperation("查询标签操作")
    @ApiOperation("标签查询")
    @GetMapping("/list")
    public BaseResponse<List<TagVO>> getTagList() {
        List<TagEntity> entityList = service.list();
        List<TagVO> voList = entityList.stream().
                map(tagEntity -> {
                    TagVO tagVO = new TagVO();
                    BeanUtils.copyProperties(tagEntity, tagVO);
                    return tagVO;
                })
                .collect(Collectors.toList());
        System.out.println("voList = " + voList);
        return ResultUtils.success(ResultCode.SUCCESS, voList);
    }
}
