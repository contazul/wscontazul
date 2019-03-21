package br.com.wscontazul.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.wscontazul.model.Ca03SomaSaldo;
import org.springframework.data.repository.query.Param;

public interface Ca03SomaSaldoRepository extends CrudRepository<Ca03SomaSaldo, Long>{
	
	List<Ca03SomaSaldo> findByNumeroContazulAndDataMovimentoBetweenOrderByValorDesc(long numeroContazul, final Date start, final Date end);

	@Query("SELECT SUM(a.valor) FROM Ca03SomaSaldo a WHERE a.numeroContazul =:numeroContazul " +
			"AND a.dataMovimento BETWEEN :startDate AND :endDate")
	Double selectTotalSomaSaldo(@Param("numeroContazul") long numeroContazul,
									 @Param("startDate") final Date start,
									 @Param("endDate") final Date end);
}
