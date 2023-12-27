package com.zl.community.model.vo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zl.community.model.entity.PostEntity;
import com.zl.community.util.JacksonUtils;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @Author : ZL
 * 帖子视图
 */
@Data
public class PostVO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 创建用户账号
     */
    private String userAccount;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 描述
     */
    private String description;

    /**
     * 标签列表
     */
    private List<String> tagList;

    /**
     * 文章种类
     */
    private String category;

    /**
     * 帖子封面
     */
    private String avatar;

    /**
     * 阅读量
     */
    private Integer viewNum;

    /**
     * 点赞数
     */
    private Integer thumbNum;

    /**
     * 收藏数
     */
    private Integer favourNum;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否屏蔽 0-否 1-是
     */
    private Integer isDeleted;

    /**
     * 帖子状态(默认 0-待审核 1-审核通过 2-审核失败)
     */
    private Integer postStatus;

    /**
     * 创建人信息
     */
    private UserVO user;

    /**
     * 是否已点赞
     */
    private Boolean hasThumb;

    /**
     * 是否已收藏
     */
    private Boolean hasFavour;

    /**
     * 包装类转对象
     *
     * @param postVO
     * @return
     */
    public static PostEntity voToObj(PostVO postVO) {
        if (postVO == null) {
            return null;
        }
        PostEntity post = new PostEntity();
        BeanUtils.copyProperties(postVO, post);
        List<String> tagList = postVO.getTagList();
        if (tagList != null) {
            post.setTags(JacksonUtils.toJson(tagList));
        }
        return post;
    }

    /**
     * 对象转包装类
     *
     * @param post
     * @return
     */
    public static PostVO objToVo(PostEntity post) {
        if (post == null) {
            return null;
        }
        PostVO postVO = new PostVO();
        BeanUtils.copyProperties(post, postVO);
        //TODO:查看数据库的Json数据是否转换为了List<String>对象
        postVO.setTagList(JacksonUtils.fromJson(post.getTags(), List.class, String.class));
        return postVO;
    }

    private static final long serialVersionUID = 1L;
}
