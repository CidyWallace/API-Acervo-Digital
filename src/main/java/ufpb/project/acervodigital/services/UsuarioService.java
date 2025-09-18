package ufpb.project.acervodigital.services;

import org.springframework.stereotype.Service;
import ufpb.project.acervodigital.exception.ItemNotFoundException;
import ufpb.project.acervodigital.exception.UserExistsException;
import ufpb.project.acervodigital.models.User;
import ufpb.project.acervodigital.models.enums.StatusUsuario;
import ufpb.project.acervodigital.repositores.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioService {
    private final UserRepository userRepository;

    public UsuarioService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User criarUsuario(User user) {
        if(userRepository.existsByEmail(user.getEmail())){
            throw new UserExistsException("O Email informado já está cadastrado");
        }
        user.setStatus(StatusUsuario.ATIVO.toString());
        user.setDataCriacao(LocalDate.now());
        userRepository.save(user);
        return user;
    }

    public List<User> listarUsuarios() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Usuário "+id+" não encontrado"));
    }

    public User atualizarUser(Long id, User user) {
        var userExistente = userRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Usuário "+id+" não encontrado"));

        userExistente.setId(user.getId());
        userExistente.setNome(user.getNome());
        userExistente.setEmail(user.getEmail());
        userExistente.setStatus(user.getStatus().toString());
        return userRepository.save(userExistente);
    }

    public User atualizarStatus(Long id, String statusUsuario) {
        var userExistente = userRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Usuário "+id+" não encontrado"));
        userExistente.setStatus(statusUsuario);
        return userRepository.save(userExistente);
    }

    public void deletaUser(Long id) {
        if(!userRepository.existsById(id)) {
            throw new ItemNotFoundException("Usuário "+id+" não encontrado");
        }
        userRepository.deleteById(id);
    }
}
