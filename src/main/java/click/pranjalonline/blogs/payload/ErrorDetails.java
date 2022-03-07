package click.pranjalonline.blogs.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
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
