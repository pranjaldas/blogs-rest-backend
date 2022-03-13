package click.pranjalonline.blogs.controller;

import click.pranjalonline.blogs.payload.PostDto;
import click.pranjalonline.blogs.payload.PostResponse;
import click.pranjalonline.blogs.service.PostService;
import click.pranjalonline.blogs.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Api(value = "Post Controller to CREATE, READ, UPDATE, DELETE posts")
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    //  SINCE WE ARE AUTOWIRED A INTERFACE HERE, IT PROVIDES MORE LOOSE COUPLING
    @Autowired
    private PostService postService;
    @Autowired
    private UserDetailsService userDetailsService;
    //  CREATE BLOG POST
    @ApiOperation(value = "API to create posts",notes = "ADMINS only")
    @PreAuthorize("hasRole('"+Constants.ROLE_ADMIN+"')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
    //  GET ALL POSTS
    @ApiOperation(value="API to get All the posts",notes = "Requires no Authentication ")
    @GetMapping
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(name = "pageNo",defaultValue = Constants.PAGE_NO_DEFAULT,required = false) int pageNo,
            @RequestParam(name = "pageSize",defaultValue = Constants.PAGE_SIZE_DEFAULT,required = false) int pageSize,
            @RequestParam(name="sortBy",defaultValue = Constants.SHORT_BY_DEFAULT,required = false) String sortBy,
            @RequestParam(name="sortDir",defaultValue = Constants.SHORT_DIRECTION_DEFAULT,required = false) String sortDir){
       return new ResponseEntity<>(postService.getAllPosts(pageNo,pageSize,sortBy,sortDir),HttpStatus.OK);
    }
    //  GET POST BY ID
    @ApiOperation(value="API to get a post by ID",notes = "Requires no Authentication ")
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
        return  new ResponseEntity<>(postService.getPostById(id),HttpStatus.OK);
    }
    //  UPDATE A POST
    @ApiOperation(value="API to update a post",notes = "ADMINS only")
    @PreAuthorize("hasRole('"+Constants.ROLE_ADMIN+"')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable(name = "id") long id){
        return new ResponseEntity<>(postService.updatePost(postDto,id),HttpStatus.OK);
    }
    // DELETE A POST
    @ApiOperation(value="API to delete a post",notes = "ADMINS only")
    @PreAuthorize("hasRole('"+Constants.ROLE_ADMIN+"')")
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){
        return new ResponseEntity<>(postService.deletePostById(id),HttpStatus.OK);
    }

}
