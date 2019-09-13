package com.sustly.service.impl;

import com.sustly.dao.BlogImageDao;
import com.sustly.entry.Blog;
import com.sustly.entry.BlogImage;
import com.sustly.service.BlogImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

/**
 * @author liyue
 * @date 2019/7/5 16:33
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class BlogImageServiceImpl implements BlogImageService {

    private final BlogImageDao blogImageDao;

    @Autowired
    public BlogImageServiceImpl(BlogImageDao blogImageDao) {
        this.blogImageDao = blogImageDao;
    }

    @Override
    public void deleteAllByBlogId(Integer blogId) {
        List<BlogImage> blogImages = blogImageDao.findByBlogId(blogId);
        for (BlogImage blogImage : blogImages) {
            String imageUrl = blogImage.getImageUrl();
            String[] split = imageUrl.split("=");
            File file = new File(split[1]);
            if (file.exists()){
                file.delete();
            }
        }
        blogImageDao.deleteAllByBlogId(blogId);
    }

    @Override
    public void saveAllImage(Blog blog) {
        if (blog.getImages() == null){
            return;
        }
        List<String> images = blog.getImages();
        blogImageDao.deleteAllByBlogId(blog.getId());
        for (String image : images){
            BlogImage blogImage = new BlogImage();
            blogImage.setBlogId(blog.getId());
            blogImage.setImageUrl(image);
            blogImageDao.save(blogImage);
        }
    }

}
