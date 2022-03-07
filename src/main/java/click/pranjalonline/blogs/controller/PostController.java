package click.pranjalonline.blogs.controller;

import click.pranjalonline.blogs.payload.PostDto;
import click.pranjalonline.blogs.payload.PostResponse;
import click.pranjalonline.blogs.service.PostService;
import click.pranjalonline.blogs.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    //  SINCE WE ARE AUTOWIRED A INTERFACE HERE, IT PROVIDES MORE LOOSE COUPLING
    @Autowired
    private PostService postService;
    //  CREATE BLOG POST
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
    //  GET ALL POSTS
    @GetMapping
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(name = "pageNo",defaultValue = Constants.PAGE_NO_DEFAULT,required = false) int pageNo,
            @RequestParam(name = "pageSize",defaultValue = Constants.PAGE_SIZE_DEFAULT,required = false) int pageSize,
            @RequestParam(name="sortBy",defaultValue = Constants.SHORT_BY_DEFAULT,required = false) String sortBy,
            @RequestParam(name="sortDir",defaultValue = Constants.SHORT_DIRECTION_DEFAULT,required = false) String sortDir){
       return new ResponseEntity<>(postService.getAllPosts(pageNo,pageSize,sortBy,sortDir),HttpStatus.OK);
    }
    //  GET POST BY ID
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
        return  new ResponseEntity<>(postService.getPostById(id),HttpStatus.OK);
    }
    //  UPDATE A POST
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable(name = "id") long id){
        return new ResponseEntity<>(postService.updatePost(postDto,id),HttpStatus.OK);
    }
    // DELETE A POST
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){
        return new ResponseEntity<>(postService.deletePostById(id),HttpStatus.OK);
    }
}
