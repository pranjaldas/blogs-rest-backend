package click.pranjalonline.blogs.controller;

import click.pranjalonline.blogs.payload.CommentDto;
import click.pranjalonline.blogs.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(value = "APIs for CREATE, READ, UPDATE, DELETE comments")
@RestController
@RequestMapping("/api/v1/posts/{post_id}/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @ApiOperation(value = "API to find all the comments of a post")
    @GetMapping
    public ResponseEntity<List<CommentDto>> findAllCommentsByPostId(@PathVariable(name = "post_id")Long post_id){
        return new ResponseEntity<>(commentService.findAllCommentsByPostId(post_id),HttpStatus.OK);
    }
    @ApiOperation(value = "API to find a the comments of a particular post")
    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> findCommentById(@PathVariable(name="post_id")Long post_id,@PathVariable(name = "id") Long id){
        return  new ResponseEntity<>(commentService.findCommentById(post_id,id),HttpStatus.OK);
    }
    @ApiOperation(value = "API to update a comment of a particular post")
    @PutMapping()
    public ResponseEntity<CommentDto> updateComment(@PathVariable(name="post_id")Long post_id,@RequestBody CommentDto commentDto){
        return  new ResponseEntity<>(commentService.updateComment(post_id,commentDto),HttpStatus.OK);
    }
    @ApiOperation(value = "API to create a comment on a particular post")
    @PostMapping
    public ResponseEntity<CommentDto> createComment(@PathVariable(name = "post_id")Long post_id, @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(post_id,commentDto), HttpStatus.CREATED);
    }
    @ApiOperation(value = "API to delete a comment")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(commentService.deleteComment(id),HttpStatus.CREATED);
    }

}
