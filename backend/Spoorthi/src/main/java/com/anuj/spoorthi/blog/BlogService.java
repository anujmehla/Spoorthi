package com.anuj.spoorthi.blog;

import java.util.List;

public interface BlogService {
    BlogDTO addBlog(BlogDTO blogDTO);

    List<BlogDTO> getAllBlogs();

    BlogDTO getBlog(long id);
}
