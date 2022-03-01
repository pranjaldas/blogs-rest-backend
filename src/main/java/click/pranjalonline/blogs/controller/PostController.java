package click.pranjalonline.blogs.controller;

import click.pranjalonline.blogs.payload.PostDto;
import click.pranjalonline.blogs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    //  SINCE WE ARE AUTOWIRED A INTERFACE HERE, IT PROVIDES MORE LOOSE COUPLING
    @Autowired
    private PostService postService;
    //  CREATE BLOG POST
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
    //  GET ALL POSTS
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(){
       return new ResponseEntity<>(postService.getAllPosts(),HttpStatus.OK);
    }
    //  GET POST BY ID
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id){
        return  new ResponseEntity<>(postService.getPostById(id),HttpStatus.OK);
    }
}
