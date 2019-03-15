package br.com.wscontazul.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.wscontazul.model.Ca02Contazul;

public interface Ca02ContazulRepository extends CrudRepository<Ca02Contazul, Integer> {
	
	List<Ca02Contazul> findByIdUsuario(long idUsuario);
	
	@Query("SELECT new Ca02Contazul(c.perfil, c.numeroContazul, c.status) FROM Ca02Contazul c WHERE c.numeroContazul =:numeroContazul")
	Ca02Contazul selectFuPerfilDaConta(@Param("numeroContazul") long numeroContazul);
	
	Ca02Contazul findByNumeroContazul(long numeroContazul);
	
	@Query("SELECT c.saldo FROM Ca02Contazul c WHERE c.numeroContazul =:numeroContazul")
	double findSaldoByNumeroContazul(@Param("numeroContazul") long numeroContazul);
}
