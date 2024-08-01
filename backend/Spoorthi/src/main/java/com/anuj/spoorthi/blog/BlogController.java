package com.anuj.spoorthi.blog;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/blogs")
@RestController
public class BlogController {

    private BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping
    public ResponseEntity<?> addBlog(@Valid @RequestBody BlogDTO blogDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldError());
        }

        BlogDTO blog = blogService.addBlog(blogDTO);

        return new ResponseEntity<>(blog, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllBlogs() {
        List<BlogDTO> blogList = blogService.getAllBlogs();

        if (blogList.isEmpty()) {
            return new ResponseEntity<>("No cards found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blogList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBlogById(@PathVariable UUID id) {
        BlogDTO blog = blogService.getBlog(id);

        if (blog == null) {
            return new ResponseEntity<>("Card not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }
}
