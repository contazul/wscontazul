package br.com.wscontazul.repository;

import br.com.wscontazul.model.Ca05LucroMensal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Ca05LucroMensalRepository extends CrudRepository<Ca05LucroMensal, Long> {

    Ca05LucroMensal findById(long id);

    List<Ca05LucroMensal> findByNumeroContazulOrderByValorDesc(long numeroContazul);

    @Query("SELECT SUM(a.valor) FROM Ca05LucroMensal a WHERE a.numeroContazul =:numeroContazul")
    Double selectTotalLucroMensal(@Param("numeroContazul") long numeroContazul);
}
