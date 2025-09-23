package uz.pdp.dbcontrol;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.pdp.dbcontrol.model.entity.AuthRole;
import uz.pdp.dbcontrol.model.entity.AuthUser;
import uz.pdp.dbcontrol.repository.AuthRoleRepository;
import uz.pdp.dbcontrol.repository.AuthUserRepository;

import java.util.Collections;

@SpringBootApplication
public class DbControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbControlApplication.class, args);
    }


    @Bean
    CommandLineRunner run(
            AuthUserRepository authUserRepository,
            AuthRoleRepository authRoleRespository,
            PasswordEncoder encoder) {
        return args -> {

            AuthRole role = new AuthRole();
            role.setCode("ADMIN");
            role.setName("Mamur");
            role.setPermissions(Collections.emptyList());

            authRoleRespository.save(role);

            AuthUser authUser = new AuthUser();
            authUser.setUsername("admin");
            authUser.setPassword(encoder.encode("123"));
            authUser.setRole(role);
            authUserRepository.save(authUser);
        };
    }

}
