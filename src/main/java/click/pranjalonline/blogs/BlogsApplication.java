package click.pranjalonline.blogs;

import click.pranjalonline.blogs.entity.Role;
import click.pranjalonline.blogs.entity.User;
import click.pranjalonline.blogs.repository.RoleRepository;
import click.pranjalonline.blogs.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;

@SpringBootApplication
public class BlogsApplication implements CommandLineRunner {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	@Bean
	PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder();};
	public static void main(String[] args) {
		SpringApplication.run(BlogsApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
//		Role roleA= new Role();
//		roleA.setName("ROLE_ADMIN");
//		roleRepository.save(roleA);
//		Role roleU= new Role();
//		roleU.setName("ROLE_USER");
//		roleRepository.save(roleU);
//
//		User user= new User();
//		user.setRoles(Collections.singleton(roleA));
//		user.setName("Pranjal Das");
//		user.setEmail("pranjaladmin@gmail.com");
//		user.setUsername("pranjal-admin");
//		user.setPassword(passwordEncoder().encode("password"));
//		userRepository.save(user);

	}
}
