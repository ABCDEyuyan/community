package com.zl.community.model.dto;

import com.google.common.reflect.TypeToken;
import com.zl.community.model.entity.PostEntity;
import com.zl.community.model.vo.PostVO;
import com.zl.community.util.JacksonUtils;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 帖子Es包装
 * @Author : ZL
 */
@Data
public class PostEsModel implements Serializable {
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表
     */
    private List<String> tags;

    /**
     * 点赞数
     */
    private Integer thumbNum;

    /**
     * 收藏数
     */
    private Integer favourNum;

    /**
     * 创建用户账号
     */
    private String userAccount;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;

    private static final long serialVersionUID = 1L;

    /**
     * 对象转包装类
     *
     * @param postEntity
     * @return
     */
    public static PostEsModel objToDto(PostEntity postEntity) {
        if (postEntity == null) {
            return null;
        }
        PostEsModel postEsDTO = new PostEsModel();
        BeanUtils.copyProperties(postEntity, postEsDTO);
        String tagsStr = postEntity.getTags();
        if (StringUtils.isNotBlank(tagsStr)) {
            postEsDTO.setTags(JacksonUtils.fromJson(tagsStr, List.class, String.class));
        }
        return postEsDTO;
    }

    /**
     * 包装类转对象
     *
     * @param postEsModel
     * @return
     */
    public static PostEntity dtoToObj(PostEsModel postEsModel) {
        if (postEsModel == null) {
            return null;
        }
        PostEntity post = new PostEntity();
        BeanUtils.copyProperties(postEsModel, post);
        List<String> tagList = postEsModel.getTags();
        if (CollectionUtils.isNotEmpty(tagList)) {
            post.setTags(JacksonUtils.toJson(tagList));
        }
        return post;
    }
}
