package click.pranjalonline.blogs.payload;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    private long id;
    private String name;
    private String email;
    private String password;
    private String username;

}
