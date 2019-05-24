package com.sustly.dao;

import com.sustly.document.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author admin
 */
public interface BlogElasticsearchRepository extends ElasticsearchRepository<EsBlog,Integer> {
    /**
     *
     * 模糊查询
     * @param content 正文
     * @param summary 摘要
     * @param title 标题
     * @param tags 标签
     * @param pageable 分页
     * @return Page
     */
    Page<EsBlog> findDistinctByContentContainingOrSummaryContainingOrTitleContainingOrTagsContaining(String content, String summary, String title, String tags , Pageable pageable);
}
