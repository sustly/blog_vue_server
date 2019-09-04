package com.sustly.dao;

import com.sustly.document.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.Modifying;

/**
 * @author admin
 */
public interface BlogElasticsearchRepository extends ElasticsearchRepository<EsBlog,Integer> {
    /**
     *
     * 模糊查询
     * @param content 正文
     * @param title 标题
     * @param category 分类
     * @param pageable 分页
     * @return Page
     */
    Page<EsBlog> findDistinctByContentContainingOrTitleContainingOrCategoryContaining(String content, String title, String category, Pageable pageable);

    /**
     * deleteByBlogId
     * @param id id
     */
    @Modifying
    void deleteByBlogId(Integer id);
}
