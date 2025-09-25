package ufpb.project.acervodigital.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ufpb.project.acervodigital.exception.BusinessRuleException;
import ufpb.project.acervodigital.exception.ItemNotFoundException;
import ufpb.project.acervodigital.models.AtivoDigital;
import ufpb.project.acervodigital.models.Emprestimo;
import ufpb.project.acervodigital.models.User;
import ufpb.project.acervodigital.models.enums.StatusEmprestimo;
import ufpb.project.acervodigital.models.enums.StatusUsuario;
import ufpb.project.acervodigital.repositores.AtivoDigitalRepository;
import ufpb.project.acervodigital.repositores.EmprestimoRepository;
import ufpb.project.acervodigital.repositores.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {

    private static final int PERIODO_EMPRESTIMO = 14;

    private final EmprestimoRepository emprestimoRepository;
    private final UserRepository userRepository;
    private final AtivoDigitalRepository ativoDigitalRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository, UserRepository userRepository, AtivoDigitalRepository ativoDigitalRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.userRepository = userRepository;
        this.ativoDigitalRepository = ativoDigitalRepository;
    }

    @Transactional
    public Emprestimo criarEmprestimo(Long id_ativo, User user) {
        var ativo = ativoDigitalRepository.findById(id_ativo)
                .orElseThrow(() -> new ItemNotFoundException("Ativo "+id_ativo+" não encontrado"));

        if (user.getStatus() == StatusUsuario.SUSPENSO){
            throw new BusinessRuleException("Usuário suspensos não podem realizar emprestimos");
        }
        if(ativo.getLicencasDisponiveis() <= 0) {
            throw new BusinessRuleException("Não há licencas disponíveis para este ativo para emprestimos");
        }
        boolean possuiEmprestimo = emprestimoRepository.existsByAtivoIdAndUserIdAndStatus(ativo.getId(), user.getId(), StatusEmprestimo.ATIVO);
        if (possuiEmprestimo) {
            throw new BusinessRuleException("Usuário já possui um emprestimo ativo para este titulo");
        }

        ativo.setLicencasDisponiveis(ativo.getLicencasDisponiveis() - 1);
        ativoDigitalRepository.save(ativo);

        Emprestimo newEmprestimo = new Emprestimo();
        newEmprestimo.setUser(user);
        newEmprestimo.setAtivo(ativo);
        newEmprestimo.setStatus(StatusEmprestimo.ATIVO);
        newEmprestimo.setDataEmprestimo(LocalDate.now());
        newEmprestimo.setDataDevolucao(LocalDate.now().plusDays(PERIODO_EMPRESTIMO));
        return emprestimoRepository.save(newEmprestimo);
    }

    @Transactional
    public Emprestimo devolucaoEmprestimo(Long idEmprestimo) {
        Emprestimo emprestimo = emprestimoRepository.findById(idEmprestimo)
                .orElseThrow(() -> new ItemNotFoundException("Emprestimo "+idEmprestimo+" não encontrado"));

        if(emprestimo.getStatus() != StatusEmprestimo.ATIVO) {
            throw new BusinessRuleException("Este empréstimo não está mais ativo e não pode ser mais devolvido");
        }

        emprestimo.setStatus(StatusEmprestimo.DEVOLVIDO);

        AtivoDigital ativo = emprestimo.getAtivo();
        ativo.setLicencasDisponiveis(ativo.getLicencasDisponiveis() + 1);
        ativoDigitalRepository.save(ativo);

        return emprestimoRepository.save(emprestimo);
    }

    public List<Emprestimo> buscarPorUsuario(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ItemNotFoundException("Usuario "+userId+" não encontrado");
        }

        return emprestimoRepository.findByUserId(userId);
    }

    public List<Emprestimo> listarEmprestimo() {
        return emprestimoRepository.findAll();
    }
}
