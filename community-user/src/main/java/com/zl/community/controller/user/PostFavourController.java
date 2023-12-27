package com.zl.community.controller.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zl.community.annotation.LogOperation;
import com.zl.community.common.BaseResponse;
import com.zl.community.common.ResultCode;
import com.zl.community.controller.BaseController;
import com.zl.community.exception.BusinessException;
import com.zl.community.model.dto.PostFavourAddModel;
import com.zl.community.model.dto.PostFavourQueryModel;
import com.zl.community.model.dto.PostQueryModel;
import com.zl.community.model.entity.PostEntity;
import com.zl.community.model.vo.PostVO;
import com.zl.community.model.vo.UserPrincipal;
import com.zl.community.service.PostFavourService;
import com.zl.community.service.PostService;
import com.zl.community.service.UserService;
import com.zl.community.util.ResultUtils;
import com.zl.community.util.ThrowUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 帖子收藏接口
 * @Author : ZL
 */
@RestController
@RequestMapping("/user/post_favour")
@Slf4j
@Api(tags = "用户帖子收藏接口")
@RequiredArgsConstructor
public class PostFavourController {
    private final PostFavourService postFavourService;

    private final PostService postService;

    /**
     * 收藏 / 取消收藏
     *
     * @param postFavourAddModel
     * @return resultNum 收藏变化数
     */
    @LogOperation("用户收藏/取消收藏帖子操作")
    @ApiOperation("用户收藏/取消收藏帖子")
    @PostMapping("/favour")
    public BaseResponse<Integer> doPostFavour(@RequestBody PostFavourAddModel postFavourAddModel) {
        if (postFavourAddModel == null || postFavourAddModel.getPostId() <= 0) {
            throw new BusinessException(ResultCode.PARAMS_ERROR);
        }
        // 登录才能操作
//        final User loginUser = userService.getLoginUser(request);
        BaseController baseController=new BaseController();
        UserPrincipal loginUser = baseController.getLoginUser();
        long postId = postFavourAddModel.getPostId();
        int result = postFavourService.doPostFavour(postId, loginUser);
        return ResultUtils.success(result);
    }

    /**
     * 获取我收藏的帖子列表
     *
     * @param postQueryModel
     */
    @LogOperation("获取我收藏的帖子操作")
    @ApiOperation("获取我收藏的帖子")
    @PostMapping("/my/list/page")
    public BaseResponse<Page<PostVO>> listMyFavourPostByPage(@RequestBody PostQueryModel postQueryModel) {
        if (postQueryModel == null) {
            throw new BusinessException(ResultCode.PARAMS_ERROR);
        }
//        User loginUser = userService.getLoginUser(request);
        BaseController baseController=new BaseController();
        UserPrincipal loginUser = baseController.getLoginUser();
        Integer pageNo = postQueryModel.getPageNo();
        Integer pageSize = postQueryModel.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(pageSize > 20, ResultCode.PARAMS_ERROR);
        Page<PostEntity> postPage = postFavourService.listFavourPostByPage(new Page<>(pageNo, pageSize),
                postService.getQueryWrapper(postQueryModel), loginUser.getUserAccount());
        return ResultUtils.success(postService.getPostVOPage(postPage));
    }

    /**
     * 获取用户收藏的帖子列表
     *
     * @param postFavourQueryModel
     */
    @LogOperation("用户收藏的帖子列表操作")
    @ApiOperation("用户收藏的帖子列表")
    @PostMapping("/list/page")
    public BaseResponse<Page<PostVO>> listFavourPostByPage(@RequestBody PostFavourQueryModel postFavourQueryModel) {
        if (postFavourQueryModel == null) {
            throw new BusinessException(ResultCode.PARAMS_ERROR);
        }
        Integer pageNo = postFavourQueryModel.getPageNo();
        Integer pageSize = postFavourQueryModel.getPageSize();
        String userAccount = postFavourQueryModel.getUserAccount();
        // 限制爬虫
        ThrowUtils.throwIf(pageSize > 20 || userAccount == null, ResultCode.PARAMS_ERROR);
        Page<PostEntity> postPage = postFavourService.listFavourPostByPage(new Page<>(pageNo, pageSize),
                postService.getQueryWrapper(postFavourQueryModel.getPostQueryModel()), userAccount);
        return ResultUtils.success(postService.getPostVOPage(postPage));
    }
}
