package ufpb.project.acervodigital.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ufpb.project.acervodigital.exception.BusinessRuleException;
import ufpb.project.acervodigital.exception.ItemNotFoundException;
import ufpb.project.acervodigital.models.Reserva;
import ufpb.project.acervodigital.models.User;
import ufpb.project.acervodigital.models.enums.StatusEmprestimo;
import ufpb.project.acervodigital.models.enums.StatusReserva;
import ufpb.project.acervodigital.models.enums.StatusUsuario;
import ufpb.project.acervodigital.repositores.AtivoDigitalRepository;
import ufpb.project.acervodigital.repositores.EmprestimoRepository;
import ufpb.project.acervodigital.repositores.ReservaRepository;
import ufpb.project.acervodigital.repositores.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final UserRepository userRepositorio;
    private final AtivoDigitalRepository ativoRepository;
    private final EmprestimoRepository emprestimoRepository;

    public ReservaService(ReservaRepository reservaRepository, UserRepository userRepository, AtivoDigitalRepository ativoDigitalRepository, EmprestimoRepository emprestimoRepository) {
        this.reservaRepository = reservaRepository;
        this.userRepositorio = userRepository;
        this.ativoRepository = ativoDigitalRepository;
        this.emprestimoRepository = emprestimoRepository;
    }

    @Transactional
    public Reserva criaReserva(Long id_ativo, User user) {
        var ativo = ativoRepository.findById(id_ativo)
                .orElseThrow(() -> new ItemNotFoundException("Ativo "+id_ativo+" não encontrado"));

        if (user.getStatus() == StatusUsuario.SUSPENSO) {
            throw new BusinessRuleException("Usuário suspensos não podem fazer reservas");
        }

        if(ativo.getLicencasDisponiveis() > 0){
            throw new BusinessRuleException("Este título está disponível para empréstimo e não pode ser reservado.");
        }

        if(reservaRepository.existsByAtivoIdAndUserIdAndStatus(ativo.getId(), user.getId(), StatusReserva.ATIVA)){
            throw new BusinessRuleException("Você já possui uma reserva ativa para este título");
        }

        if (emprestimoRepository.existsByAtivoIdAndUserIdAndStatus(ativo.getId(), user.getId(), StatusEmprestimo.ATIVO)) {
            throw new BusinessRuleException("Você não pode reservar um título que já pegou emprestado.");
        }

        Reserva newReserva = new Reserva();
        newReserva.setUser(user);
        newReserva.setAtivo(ativo);
        newReserva.setDataReserva(LocalDateTime.now());
        newReserva.setStatus(StatusReserva.ATIVA);
        return reservaRepository.save(newReserva);
    }

    @Transactional
    public void cancelaReserva(Long reservaId, Long userId) {
        var reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new ItemNotFoundException("Reserva "+reservaId+" não encontrado."));

        if(!reserva.getUser().getId().equals(userId)){
            throw new BusinessRuleException("Você não tem permissão para cancelar está reserva.");
        }

        if(reserva.getStatus() != StatusReserva.ATIVA){
            throw new BusinessRuleException("Apenas reservas ativas podem ser canceladas.");
        }

        reserva.setStatus(StatusReserva.CANCELADA);
        reservaRepository.save(reserva);
    }

    public List<Reserva> buscarPorUserId(Long userId) {
        if(!userRepositorio.existsById(userId)){
            throw new ItemNotFoundException("Usuário "+userId+" não encontrado.");
        }

        return reservaRepository.findByUserIdAndStatus(userId, StatusReserva.ATIVA);
    }

    public Long PosicaoReserva(Reserva reserva) {
        return reservaRepository.countByAtivoIdAndStatusAndIdLessThan(reserva.getAtivo().getId(), StatusReserva.ATIVA, reserva.getId()) + 1;
    }
}
