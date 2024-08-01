package com.anuj.spoorthi.blog;

import com.anuj.spoorthi.customexceptions.BlogNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    @Autowired
    BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public BlogDTO addBlog(BlogDTO blogDTO) {
        log.info("addBlog");

        BlogEntity blog  = new BlogEntity();
        blog.setTitle(blogDTO.title());
        blog.setImage(blogDTO.image());
        blog.setContent(blogDTO.content());
        blog.setPublishDate(blogDTO.publishDate());
        blog.setAuthorName(blogDTO.authorName());

        BlogEntity blogEntity = null;
        try {
            blogEntity = blogRepository.save(blog);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (blogEntity != null) {
            BeanUtils.copyProperties(blogEntity,blogDTO);
        }

        return blogDTO;
    }

    @Override
    public List<BlogDTO> getAllBlogs() {
        log.info("getAllBlogs");
        List<BlogEntity> blogEntities = blogRepository.findAll();
        List<BlogDTO> blogsDTO = new ArrayList<>();

        blogEntities.forEach(blog -> {
            BlogDTO dto = new BlogDTO(
                    blog.getTitle(),
                    blog.getContent(),
                    blog.getImage(),
                    blog.getPublishDate(),
                    blog.getAuthorName()
            );
            blogsDTO.add(dto);
        });
        return blogsDTO;
    }

    @Override
    public BlogDTO getBlog(UUID id) {
        log.info("getBlog");
        BlogEntity blog = blogRepository.findById(id)
                .orElseThrow(() -> new BlogNotFoundException("Blog not found with id "+id));

        return new BlogDTO(
                blog.getTitle(),
                blog.getImage(),
                blog.getContent(),
                blog.getPublishDate(),
                blog.getAuthorName()
        );
    }

}
