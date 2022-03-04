package click.pranjalonline.blogs.controller;

import click.pranjalonline.blogs.entity.Comment;
import click.pranjalonline.blogs.payload.CommentDto;
import click.pranjalonline.blogs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/{post_id}/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentDto>> findAllCommentsByPostId(@PathVariable(name = "post_id")Long post_id){
        return new ResponseEntity<>(commentService.findAllCommentsByPostId(post_id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@PathVariable(name = "post_id")Long post_id, @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(post_id,commentDto), HttpStatus.CREATED);
    }

}
