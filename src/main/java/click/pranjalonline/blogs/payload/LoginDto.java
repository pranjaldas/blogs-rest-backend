package click.pranjalonline.blogs.payload;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "Login DTO model")
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}
