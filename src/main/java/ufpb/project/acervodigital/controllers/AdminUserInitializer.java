package ufpb.project.acervodigital.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ufpb.project.acervodigital.models.User;
import ufpb.project.acervodigital.models.enums.Role;
import ufpb.project.acervodigital.models.enums.StatusUsuario;
import ufpb.project.acervodigital.repositores.UserRepository;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(AdminUserInitializer.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminUserInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        String emailAdmin = "admin@bibliotech.com";

        if(userRepository.findByEmail(emailAdmin).isEmpty()) {
            log.info("Admin user does not exist");

            User user = new User();
            user.setNome("Admin");
            user.setEmail(emailAdmin);
            user.setNumeroCartao("ADMIN001");
            user.setSenha(passwordEncoder.encode("admin123"));
            user.setRole(Role.ADMIN.toString());
            user.setStatus(StatusUsuario.ATIVO);
            userRepository.save(user);
            log.info("Usuário administrador padrão criado com sucesso! Email: '{}'", emailAdmin);
        }else {
            log.info("Usuário administrador já existe. Nenhuma ação necessária.");
        }
    }
}
