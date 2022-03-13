package click.pranjalonline.blogs.payload;

import click.pranjalonline.blogs.entity.Comment;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@ApiModel(value = "Comment Data Transfer Object")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private long id;
    private String name;
    private String email;
    private String body;
    public CommentDto(Comment comment){
        this.id= comment.getId();
        this.name = comment.getName();
        this.email = comment.getEmail();
        this.body = comment.getBody();
    }
}
