package click.pranjalonline.blogs.service.impl;

import click.pranjalonline.blogs.entity.User;
import click.pranjalonline.blogs.payload.RegisterDto;
import click.pranjalonline.blogs.repository.RoleRepository;
import click.pranjalonline.blogs.repository.UserRepository;
import click.pranjalonline.blogs.service.UserService;
import click.pranjalonline.blogs.utils.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String userName) {
        return userRepository.existsByUsername(userName);
    }

    @Override
    public RegisterDto createUser(RegisterDto registerDto) {
        User user= mapper.map(registerDto, User.class);
        user.setRoles(Collections.singleton(roleRepository.findByName(Constants.ROLE_USER)));
        userRepository.save(user);
        return registerDto;
    }
}
