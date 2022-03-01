package click.pranjalonline.blogs.payload;

import click.pranjalonline.blogs.entity.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDto {
    private long id;
    private String title;
    private String description;
    private String content;
    public PostDto(Post post){
        this.id= post.getId();
        this.title= post.getTitle();
        this.description= post.getDescription();
        this.content= post.getContent();

    }
}
