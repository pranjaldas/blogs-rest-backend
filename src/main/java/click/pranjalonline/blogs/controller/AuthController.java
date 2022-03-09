package click.pranjalonline.blogs.controller;

import click.pranjalonline.blogs.payload.LoginDto;
import click.pranjalonline.blogs.payload.RegisterDto;
import click.pranjalonline.blogs.repository.RoleRepository;
import click.pranjalonline.blogs.service.UserService;
import click.pranjalonline.blogs.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),loginDto.getPassword()));
        authentication.getPrincipal();
        if(authentication.isAuthenticated())
            return  new ResponseEntity<>("Login Successfully", HttpStatus.OK);
        else
            return  new ResponseEntity<>("Login Failed", HttpStatus.OK);

    }
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterDto registerDto){
        if(userService.existsByEmail(registerDto.getEmail()))
            return new ResponseEntity<>("Email already exists",HttpStatus.BAD_REQUEST);
        if(userService.existsByUsername(registerDto.getUsername()))
            return new ResponseEntity<>("Username already exists",HttpStatus.BAD_REQUEST);
        registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        userService.createUser(registerDto);

        return  new ResponseEntity<>("User Created successfully",HttpStatus.CREATED);
    }

}