package com.zl.community.controller.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zl.community.annotation.LogOperation;
import com.zl.community.common.BaseResponse;
import com.zl.community.common.ResultCode;
import com.zl.community.controller.BaseController;
import com.zl.community.exception.BusinessException;
import com.zl.community.model.dto.*;
import com.zl.community.model.entity.PostEntity;
import com.zl.community.model.vo.PostVO;
import com.zl.community.model.vo.UserPrincipal;
import com.zl.community.service.PostService;
import com.zl.community.util.JacksonUtils;
import com.zl.community.util.ResultUtils;
import com.zl.community.util.ThrowUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author : ZL
 */
@Slf4j
@RequestMapping("/user/post")
@RestController
@Api(tags = "用户帖子接口")
@RequiredArgsConstructor
public class PostController extends BaseController {
    private final PostService postService;

    /**
     * 用户创建帖子
     *
     * @param postCreateModel
     * @return
     */
    @LogOperation("用户创建帖子操作")
    @ApiOperation("用户创建帖子")
    @PostMapping("/add")
    public BaseResponse<Long> addPost(@RequestBody PostCreateModel postCreateModel) {
        if (postCreateModel == null) {
            throw new BusinessException(ResultCode.PARAMS_ERROR);
        }
        PostEntity post = new PostEntity();
        BeanUtils.copyProperties(postCreateModel, post);
        List<String> tags = postCreateModel.getTags();
        if (tags != null) {
            post.setTags(JacksonUtils.toJson(tags));
        }
        postService.validPost(post, true);
        post.setUserAccount(getUserAccount());
        post.setFavourNum(0);
        post.setThumbNum(0);
        boolean result = postService.save(post);
        ThrowUtils.throwIf(!result, ResultCode.OPERATION_ERROR);
        long newPostId = post.getId();
        return ResultUtils.success(ResultCode.SUCCESS,newPostId);
    }

    /**
     * 删除
     *
     * @param operateByIdModel
     * @return
     */
    @LogOperation("用户删除帖子操作")
    @ApiOperation("用户删除帖子")
    @PostMapping("/delete")
    public BaseResponse<Boolean> deletePost(@RequestBody OperateByIdModel operateByIdModel) {
        if (operateByIdModel == null || operateByIdModel.getId() <= 0) {
            throw new BusinessException(ResultCode.PARAMS_ERROR);
        }
        long id = operateByIdModel.getId();
        // 判断是否存在
        PostEntity oldPost = postService.getById(id);
        ThrowUtils.throwIf(oldPost == null, ResultCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可删除
        //TODO:管理员判断
//        if (!oldPost.getUserAccount().equals(getUserAccount()) || !ADMIN.equals(getUserRole())) {
        if (!oldPost.getUserAccount().equals(getUserAccount())) {
            throw new BusinessException(ResultCode.NO_AUTH_ERROR);
        }
        boolean b = postService.removeById(id);
        return ResultUtils.success(ResultCode.SUCCESS,b);
    }

//    /**
//     * 更新（仅管理员）
//     *
//     * @param postUpdateRequest
//     * @return
//     */
//    @PostMapping("/update")
//    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
//    public BaseResponse<Boolean> updatePost(@RequestBody PostUpdateRequest postUpdateRequest) {
//        if (postUpdateRequest == null || postUpdateRequest.getId() <= 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        PostApi post = new PostApi();
//        BeanUtils.copyProperties(postUpdateRequest, post);
//        List<String> tags = postUpdateRequest.getTags();
//        if (tags != null) {
//            post.setTags(GSON.toJson(tags));
//        }
//        // 参数校验
//        postService.validPost(post, false);
//        long id = postUpdateRequest.getId();
//        // 判断是否存在
//        PostApi oldPost = postService.getById(id);
//        ThrowUtils.throwIf(oldPost == null, ErrorCode.NOT_FOUND_ERROR);
//        boolean result = postService.updateById(post);
//        return ResultUtils.success(ResultCode.SUCCESS,result);
//    }

    /**
     * 编辑（用户）
     *
     * @param postEditModel
     * @return
     */
    @LogOperation("用户编辑帖子操作")
    @ApiOperation("用户编辑帖子")
    @PostMapping("/edit")
    public BaseResponse<Boolean> editPost(@RequestBody PostEditModel postEditModel) {
        if (postEditModel == null || postEditModel.getId() <= 0) {
            throw new BusinessException(ResultCode.PARAMS_ERROR);
        }
        PostEntity post = new PostEntity();
        BeanUtils.copyProperties(postEditModel, post);
        List<String> tags = postEditModel.getTags();
        if (tags != null) {
            post.setTags(JacksonUtils.toJson(tags));
        }
        // 参数校验
        postService.validPost(post, false);
        BaseController baseController = new BaseController();
//        User loginUser = userService.getLoginUser(request);
//        long id = postEditRequest.getId();
        UserPrincipal loginUser = baseController.getLoginUser();
        // 判断是否存在
        PostEntity oldPost = postService.getById(loginUser);
        ThrowUtils.throwIf(oldPost == null, ResultCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可编辑
        //TODO:管理员判断
//        if (!oldPost.getUserAccount().equals(loginUser.getUserAccount()) && !userService.isAdmin(loginUser)) {
        if (!oldPost.getUserAccount().equals(loginUser.getUserAccount()) ) {
            throw new BusinessException(ResultCode.NO_AUTH_ERROR);
        }
        boolean result = postService.updateById(post);
        return ResultUtils.success(ResultCode.SUCCESS,result);
    }

    /**
     * 根据 id 获取帖子
     *
     * @param id
     * @return
     */
    @LogOperation("根据id获取帖子操作")
    @ApiOperation("根据id获取帖子")
    @GetMapping("/get/vo")
    public BaseResponse<PostVO> getPostVOById(long id) {
        if (id <= 0) {
            throw new BusinessException(ResultCode.PARAMS_ERROR);
        }
        PostEntity post = postService.getById(id);
        if (post == null) {
            throw new BusinessException(ResultCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(ResultCode.SUCCESS,postService.getPostVO(post));
    }

    /**
     * 分页获取帖子列表（封装类）
     *
     * @param postQueryModel
     * @return
     */
    @LogOperation("分页获取帖子列表（主页）操作")
    @ApiOperation("分页获取帖子列表（主页）")
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<PostVO>> listPostVOByPage(@RequestBody PostQueryModel postQueryModel) {
        Integer pageNo = postQueryModel.getPageNo();
        Integer pageSize = postQueryModel.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(pageSize > 20, ResultCode.PARAMS_ERROR);
        Page<PostEntity> postPage = postService.page(new Page<>(pageNo, pageSize),
                postService.getQueryWrapper(postQueryModel));
        return ResultUtils.success(ResultCode.SUCCESS,postService.getPostVOPage(postPage));
    }

    /**
     * 分页获取当前用户创建的资源列表
     *
     * @param postQueryModel
     * @return
     */
    @LogOperation("分页获取当前用户创建的资源列表（用户个人中心）操作")
    @ApiOperation("分页获取当前用户创建的资源列表（用户个人中心）")
    @PostMapping("/my/list/page/vo")
    public BaseResponse<Page<PostVO>> listMyPostVOByPage(@RequestBody PostQueryModel postQueryModel) {
        if (postQueryModel == null) {
            throw new BusinessException(ResultCode.PARAMS_ERROR);
        }
        BaseController baseController = new BaseController();
        UserPrincipal loginUser = baseController.getLoginUser();
//        UserEntity loginUser = userService.getLoginUser(request);
        postQueryModel.setUserAccount(loginUser.getUserAccount());
        Integer pageNo = postQueryModel.getPageNo();
        Integer pageSize = postQueryModel.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(pageSize > 20, ResultCode.PARAMS_ERROR);
        Page<PostEntity> postPage = postService.page(new Page<>(pageNo, pageSize),
                postService.getQueryWrapper(postQueryModel));
        return ResultUtils.success(ResultCode.SUCCESS,postService.getPostVOPage(postPage));
    }

    /**
     * 分页搜索（从 ES 查询，封装类）
     *
     * @param postQueryModel
     * @return
     */
    @LogOperation("分页搜索（ES）操作")
    @ApiOperation("分页搜索（ES）")
    @PostMapping("/search/page/vo")
    public BaseResponse<Page<PostVO>> searchPostVOByPage(@RequestBody PostQueryModel postQueryModel) {
        Integer pageSize = postQueryModel.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(pageSize > 20, ResultCode.PARAMS_ERROR);
        Page<PostEntity> postPage = postService.searchFromEs(postQueryModel);
        return ResultUtils.success(ResultCode.SUCCESS,postService.getPostVOPage(postPage));
    }

}
