package com.zl.community.controller.user;

import com.zl.community.annotation.LogOperation;
import com.zl.community.common.BaseResponse;
import com.zl.community.common.ResultCode;
import com.zl.community.controller.BaseController;
import com.zl.community.exception.BusinessException;
import com.zl.community.mapper.PostThumbMapper;
import com.zl.community.model.dto.PostThumbAddModel;
import com.zl.community.model.vo.UserPrincipal;
import com.zl.community.service.PostThumbService;
import com.zl.community.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author : ZL
 * 帖子点赞接口
 */
@RestController
@RequestMapping("/user/post_thumb")
@Slf4j
@Api(tags = "用户帖子点赞接口")
@RequiredArgsConstructor
public class PostThumbController {
    private final PostThumbService postThumbService;
    /**
     * 点赞 / 取消点赞
     *
     * @param postThumbAddModel
     * @return resultNum 本次点赞变化数
     */
    @LogOperation("用户点赞/取消点赞帖子操作")
    @ApiOperation("用户点赞/取消点赞帖子")
    @PostMapping("/thumb")
    public BaseResponse<Integer> doThumb(@RequestBody PostThumbAddModel postThumbAddModel) {
        if (postThumbAddModel == null || postThumbAddModel.getPostId() <= 0) {
            throw new BusinessException(ResultCode.PARAMS_ERROR);
        }
        // 登录才能点赞
        BaseController baseController=new BaseController();
        UserPrincipal loginUser = baseController.getLoginUser();
        long postId = postThumbAddModel.getPostId();
        int result = postThumbService.doPostThumb(postId, loginUser);
        return ResultUtils.success(result);
    }
}
