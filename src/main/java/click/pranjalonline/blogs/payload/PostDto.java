package click.pranjalonline.blogs.payload;

import click.pranjalonline.blogs.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private long id;
    @NotEmpty
    @Size(min = 2,message = "Title should be more than 2 characters")
    private String title;
    @NotEmpty
    @Size(min = 10,message = "Title should be more than 2 characters")
    private String description;
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;
    public PostDto(Post post){
        this.id= post.getId();
        this.title= post.getTitle();
        this.description= post.getDescription();
        this.content= post.getContent();

    }
}
