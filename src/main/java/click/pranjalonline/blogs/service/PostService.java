package click.pranjalonline.blogs.service;

import click.pranjalonline.blogs.entity.Post;
import click.pranjalonline.blogs.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPosts(int pageNo,int pageSize);
    PostDto getPostById(Long id);
    PostDto updatePost(PostDto postDto, Long id);

    String deletePostById(Long id);
}
