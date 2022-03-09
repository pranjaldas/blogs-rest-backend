package click.pranjalonline.blogs.service;

import click.pranjalonline.blogs.payload.RegisterDto;
import org.springframework.stereotype.Service;

public interface UserService {
    boolean existsByEmail(String email);
    boolean existsByUsername(String userName);
    RegisterDto createUser(RegisterDto registerDto);
}
