package br.com.wscontazul.repository;

import br.com.wscontazul.model.Ca04SubtracaoSaldo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface Ca04SubtracaoSaldoRepository extends CrudRepository<Ca04SubtracaoSaldo, Long> {

    List<Ca04SubtracaoSaldo> findByNumeroContazulAndDataMovimentoBetweenOrderByValorDesc(long numeroContazul, final Date start, final Date end);

    @Query("SELECT SUM(a.valor) FROM Ca04SubtracaoSaldo a WHERE a.numeroContazul =:numeroContazul " +
            "AND a.prioridade =:prioridade " +
            "AND a.dataMovimento BETWEEN :startDate AND :endDate")
    double selectTotalSubtracaoSaldoBaixaPrioridade(@Param("numeroContazul") long numeroContazul,
                                                    @Param("startDate") final Date start,
                                                    @Param("endDate") final Date end,
                                                    @Param("prioridade") String prioridade);

    @Query("SELECT SUM(a.valor) FROM Ca04SubtracaoSaldo a WHERE a.numeroContazul =:numeroContazul " +
            "AND a.dataMovimento BETWEEN :startDate AND :endDate")
    double selectTotalSubtracaoSaldo(@Param("numeroContazul") long numeroContazul,
                                                    @Param("startDate") final Date start,
                                                    @Param("endDate") final Date end);


}
