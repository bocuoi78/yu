package com.vku.bocuoi.yu.mapper;

import com.vku.bocuoi.yu.model.dto.CommentDto;
import com.vku.bocuoi.yu.model.dto.PostDto;
import com.vku.bocuoi.yu.model.entity.Comment;
import com.vku.bocuoi.yu.model.entity.Post;
import com.vku.bocuoi.yu.repository.MenuRepository;
import jakarta.persistence.EntityNotFoundException;

public class PostMapper {
    MenuRepository menuRepository;
    private static PostMapper INSTANCE;
    public static PostMapper getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new PostMapper();
        }
        return INSTANCE;
    }
    public PostDto toDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setMenuId(post.getMenu().getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        return postDto;
    }
    public Post toEntity(PostDto postDto) {
        Post post = new Post();
        post.setId(postDto.getId());
        post.setMenu(menuRepository.findById(postDto.getMenuId())
                .orElseThrow(()-> new EntityNotFoundException(String.format(
                        "Menu with id [%d] was not found!",
                        postDto.getMenuId())
                ))
        );
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        return post;
    }
}
