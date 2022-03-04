package click.pranjalonline.blogs.service;

import click.pranjalonline.blogs.payload.PostDto;
import click.pranjalonline.blogs.payload.PostResponse;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir);
    PostDto getPostById(Long id);
    PostDto updatePost(PostDto postDto, Long id);

    String deletePostById(Long id);
}
