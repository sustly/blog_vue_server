package com.sustly.service.impl;

import com.sustly.dao.ArticleDao;
import com.sustly.entry.Blog;
import com.sustly.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author liyue
 * @date 2019/6/20 15:46
 */
@Service
@Transactional(rollbackOn = {Exception.class})
public class ArticleServiceImpl implements ArticleService {

    private final ArticleDao articleDao;

    @Autowired
    public ArticleServiceImpl(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Override
    public void save(Blog blog) {
        articleDao.save(blog);
    }

    @Override
    public Blog findById(Integer id) {
        return articleDao.getBlogById(id);
    }

    @Override
    public void delete(Integer id) {
        articleDao.deleteById(id);
    }

    @Override
    public long getAllCount() {
        return articleDao.count();
    }

    @Override
    public List<Blog> getBlogListByPage(Integer page) {
        Sort sort=new Sort(Sort.Direction.DESC,"createTime");
        Pageable pageable = PageRequest.of(page, 10, sort);
        return articleDao.findAll(pageable).getContent();
    }

    @Override
    public List<Blog> getBlogListByView(Integer page) {
        Sort sort=new Sort(Sort.Direction.DESC,"views");
        Pageable pageable = PageRequest.of(page, 10, sort);
        return articleDao.findAll(pageable).getContent();
    }

    @Override
    public long getAllCountByCategory(String category) {
        return articleDao.countByCategory(category);
    }

    @Override
    public List<Blog> getBlogListByCategory(Integer page, String category) {
        Sort sort=new Sort(Sort.Direction.DESC,"createTime");
        Pageable pageable = PageRequest.of(page, 10, sort);
        Specification<Blog> specification = (Specification<Blog>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            predicate.getExpressions().add(criteriaBuilder.equal(root.get("category"), category));
            return predicate;
        };
        return articleDao.findAll(specification,pageable).getContent();
    }
}
