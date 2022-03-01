package click.pranjalonline.blogs.service.impl;

import click.pranjalonline.blogs.entity.Post;
import click.pranjalonline.blogs.payload.PostDto;
import click.pranjalonline.blogs.repository.PostRepository;
import click.pranjalonline.blogs.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService implements click.pranjalonline.blogs.service.PostService {
    @Autowired
    private  PostRepository postRepository;

    @Override
    public PostDto createPost(PostDto postDto) {
        //  CONVERT DTO TO ENTITY
        Post post= new Post(postDto);
        Post newPost= postRepository.save(post);
        //  CONVERT ENTITY TO DTO
        return  new PostDto(newPost);
    }

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream().map(i->new PostDto(i)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post= postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id.toString()));
        return new PostDto(post);

    }
}
