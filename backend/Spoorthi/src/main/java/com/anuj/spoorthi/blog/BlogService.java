package com.anuj.spoorthi.blog;

import java.util.List;
import java.util.UUID;

public interface BlogService {
    BlogDTO addBlog(BlogDTO blogDTO);

    List<BlogDTO> getAllBlogs();

    BlogDTO getBlog(UUID id);
}
