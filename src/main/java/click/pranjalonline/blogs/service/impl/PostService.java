package click.pranjalonline.blogs.service.impl;

import click.pranjalonline.blogs.entity.Post;
import click.pranjalonline.blogs.payload.PostDto;
import click.pranjalonline.blogs.repository.PostRepository;
import click.pranjalonline.blogs.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<PostDto> getAllPosts(int pageNo,int pageSize) {
        Pageable pageable= PageRequest.of(pageNo,pageSize);
        Page<Post> posts= postRepository.findAll(pageable);

        //  I AM USING THE MAP FUNCTION TO CONVERT POST TO POST DTO OBJECT
        //  PASSING POST OBJECT TO POST DTO CONSTRUCTOR
        return posts.getContent().stream().map(i->new PostDto(i)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post= postRepository
                .findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id.toString()));
        return new PostDto(post);

    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Posts","id",id.toString()));
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        post.setTitle(postDto.getTitle());
        return new PostDto(postRepository.save(post));
    }

    @Override
    public String deletePostById(Long id) {
        Post post = new Post();
        postRepository.delete(postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Post","id",id.toString())));


        return "POST DELETED SUCCESSFULLY";
    }
}
