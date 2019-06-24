package com.sustly.dao;

import com.sustly.entry.BlogCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author admin
 */
public interface BlogCategoryDao extends JpaRepository<BlogCategory, Integer> {
}
