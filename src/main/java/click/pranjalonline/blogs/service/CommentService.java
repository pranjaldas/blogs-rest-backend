package click.pranjalonline.blogs.service;

import click.pranjalonline.blogs.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long post_id,CommentDto commentDto);
    List<CommentDto> findAllCommentsByPostId(Long post_id);

    String deleteComment(Long id);

    CommentDto updateComment(Long post_id, CommentDto commentDto);

    CommentDto findCommentById(Long post_id, Long id);
}
