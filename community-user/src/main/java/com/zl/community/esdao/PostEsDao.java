package com.zl.community.esdao;

import com.zl.community.model.dto.PostEsModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * 帖子 ES 操作
 *
 */
public interface PostEsDao extends ElasticsearchRepository<PostEsModel, String> {

}