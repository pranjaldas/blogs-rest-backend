package click.pranjalonline.blogs.service.impl;

import click.pranjalonline.blogs.entity.Comment;
import click.pranjalonline.blogs.entity.Post;
import click.pranjalonline.blogs.payload.CommentDto;
import click.pranjalonline.blogs.repository.CommentRepository;
import click.pranjalonline.blogs.repository.PostRepository;
import click.pranjalonline.blogs.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService implements click.pranjalonline.blogs.service.CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Override
    public CommentDto createComment(Long post_id,CommentDto commentDto) {
        Post post = postRepository.findById(post_id)
                .orElseThrow(()->new ResourceNotFoundException("Post","id",post_id.toString()));
        Comment comment = new Comment(commentDto);
        comment.setPost(post);

        return new CommentDto(commentRepository.save(comment));

    }

    @Override
    public List<CommentDto> findAllCommentsByPostId(Long post_id) {
        List<CommentDto> commentDtoList= commentRepository.findAllCommentsByPostId(post_id)
                .stream().map((i)->new CommentDto(i)).collect(Collectors.toList());
        return commentDtoList;
    }
}
