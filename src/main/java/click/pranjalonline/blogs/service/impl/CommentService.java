package click.pranjalonline.blogs.service.impl;

import click.pranjalonline.blogs.entity.Comment;
import click.pranjalonline.blogs.entity.Post;
import click.pranjalonline.blogs.exceptions.BusinessLogicException;
import click.pranjalonline.blogs.payload.CommentDto;
import click.pranjalonline.blogs.repository.CommentRepository;
import click.pranjalonline.blogs.repository.PostRepository;
import click.pranjalonline.blogs.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Override
    public String deleteComment(Long id) {
        commentRepository.delete(commentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(
                "Comment","id",id.toString()
        )));
        return "Comment Deleted Successfully";
    }

    @Override
    public CommentDto updateComment(Long post_id, CommentDto commentDto) {
        Post post = postRepository.findById(post_id).orElseThrow(
                ()-> new ResourceNotFoundException("posts","id",post_id.toString()));
        Comment comment = commentRepository.findById(commentDto.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("comments","id",String.valueOf(commentDto.getId()))
        );
        if(comment.getPost().getId() != post.getId())
            throw new BusinessLogicException(HttpStatus.NOT_FOUND,"Comment does not belong to the post");
        else{
            Comment commentToUpdate = new Comment(commentDto);
            commentToUpdate.setPost(post);
            return new CommentDto(commentRepository.save(commentToUpdate));
        }

    }

    @Override
    public CommentDto findCommentById(Long post_id, Long id) {
        Post post = postRepository.findById(post_id).orElseThrow(
                ()-> new ResourceNotFoundException("posts","id",post_id.toString()));
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("comments","id",id.toString())
        );
        if(comment.getPost().getId() != post.getId())
            throw new BusinessLogicException(HttpStatus.BAD_REQUEST,"Comment does not belong to the post");
        else
            return  new CommentDto(comment);
    }
}
