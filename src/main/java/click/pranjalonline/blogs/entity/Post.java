package click.pranjalonline.blogs.entity;

import click.pranjalonline.blogs.payload.PostDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="posts",uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Post {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "id",nullable = false)
    private long id;
    @Column(name = "title",nullable = false)
    private String title;
    @Column(name = "description",nullable = false)
    private String description;
    @Column(name = "content",nullable = false)
    private String content;
    public Post(PostDto postDto){
        this.id= postDto.getId();
        this.title= postDto.getTitle();
        this.content= postDto.getContent();
        this.description= postDto.getDescription();
    }
}
