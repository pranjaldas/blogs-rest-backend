package click.pranjalonline.blogs.payload;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
@ApiModel(value = "Error Details Model")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails<T> {
    private Date timestamp;
    private String status;
    private String message;
    private T details;
}
