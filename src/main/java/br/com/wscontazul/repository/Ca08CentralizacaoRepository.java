package br.com.wscontazul.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.wscontazul.model.Ca08Centralizacao;

public interface Ca08CentralizacaoRepository extends CrudRepository<Ca08Centralizacao, Long> {
	
	@Query("SELECT a.numeroContazulCentralizada FROM Ca08Centralizacao a WHERE a.numeroContazulCentralizadora =:numeroContazulCentralizadora")
	long[] findNumeroContazulCentralizadaByNumeroContazulCentralizadora(@Param("numeroContazulCentralizadora") long numeroContazulCentralizadora);
	
	@Query("SELECT a.id FROM Ca08Centralizacao a WHERE a.numeroContazulCentralizadora =:numeroContazulCentralizadora")
	long[] findIdByNumeroContazulCentralizadora(@Param("numeroContazulCentralizadora") long numeroContazulCentralizadora);
	
	@Query("SELECT a FROM Ca08Centralizacao a WHERE a.numeroContazulCentralizada =:numeroContazulCentralizada")
	Ca08Centralizacao findIdByNumeroContazulCentralizada(@Param("numeroContazulCentralizada") long numeroContazulCentralizada);
}
