package click.pranjalonline.blogs.entity;

import click.pranjalonline.blogs.payload.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String body;
    private String email;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id",nullable = false)
    private Post post;

    public Comment(CommentDto commentDto){
        this.id= commentDto.getId();
        this.name= commentDto.getName();
        this.body=commentDto.getBody();
        this.email= commentDto.getEmail();
    }


}
