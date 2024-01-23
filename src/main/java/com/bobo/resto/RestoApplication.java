package com.bobo.resto;

import com.bobo.resto.customer.entity.Privilege;
import com.bobo.resto.customer.entity.Role;
import com.bobo.resto.customer.repository.PrivilegeRepository;
import com.bobo.resto.customer.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.bobo.resto.shared.utils.Constant.*;

@SpringBootApplication
public class RestoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(RoleRepository roleRepository,
							 PrivilegeRepository privilegeRepository) {
		return args -> {
			Collection<Privilege> privileges = new ArrayList<>();

			if(privilegeRepository.findAll().isEmpty()) {
				privileges.add(new Privilege(READ));
				privileges.add(new Privilege(WRITE));
				privileges.add(new Privilege(DELETE));

				privilegeRepository.saveAll(privileges);
			}

			if(roleRepository.findAll().isEmpty()) {
				List<Role> roles = new ArrayList<>();
				roles.add(new Role(SYS_ADMIN,privileges));
				roleRepository.saveAll(roles);
			}
		};
	}
}