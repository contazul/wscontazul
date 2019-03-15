package br.com.wscontazul.repository;

import br.com.wscontazul.model.Ca05LucroMensal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Ca05LucroMensalRepository extends CrudRepository<Ca05LucroMensal, Long> {

    Ca05LucroMensal findById(long id);

    List<Ca05LucroMensal> findByNumeroContazulOrderByValorDesc(long numeroContazul);
}
