package com.testetecnico.teste.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.testetecnico.teste.entities.User;
import com.testetecnico.teste.entities.Reclamacao;

public interface ReclamacaoRepository extends JpaRepository<Reclamacao, Long> {

    List<Reclamacao> findByClient(User client);
}
