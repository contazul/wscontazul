package br.com.wscontazul.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wscontazul.model.Ca02Contazul;
import br.com.wscontazul.model.Ca06DividaMensal;
import br.com.wscontazul.model.Ca07Meta;
import br.com.wscontazul.model.presenter.ListaMeta;
import br.com.wscontazul.repository.Ca02ContazulRepository;
import br.com.wscontazul.repository.Ca05LucroMensalRepository;
import br.com.wscontazul.repository.Ca06DividaMensalRepository;
import br.com.wscontazul.repository.Ca07MetaRepository;
import br.com.wscontazul.statics.ContasAPagar;
import br.com.wscontazul.util.UtilMeta;

@RestController
@RequestMapping("/wscontazul/meta")
public class WsContazulMeta {
	
	@Autowired
	private Ca07MetaRepository metaR;
	
	@Autowired
	private Ca06DividaMensalRepository dividaMensalR;
	
	@Autowired
	private Ca05LucroMensalRepository lucroMensalR;
	
	@Autowired
	private Ca02ContazulRepository contazulR;
	
	@PostMapping("/incluirMeta")
	public void incluirMeta(String descricao, double valor, int isAvista, double valorEconomizar, 
			int quantidadeParcela, long numeroContazul) {
		
		Ca07Meta ca07Meta = new Ca07Meta(descricao, valor, isAvista, valorEconomizar, numeroContazul, quantidadeParcela, 0);
		metaR.save(ca07Meta);
	}
	
	@GetMapping("/listaMeta")
	public List<ListaMeta> listaMeta(long numeroContazul) {
		
		double totalDivida = dividaMensalR.selectTotalDividaMensal(numeroContazul);
		double totalBeneficio = lucroMensalR.selectTotalLucroMensal(numeroContazul);
		
		List<Ca07Meta> metas = metaR.findByNumeroContazulAndIsRealizada(numeroContazul, 0);
		
		List<ListaMeta> lista = new ArrayList<>();
		
		for(Ca07Meta meta : metas) {
			
			UtilMeta util = new UtilMeta();
			
			ListaMeta listaMeta = new ListaMeta(
					util.calcularStatus(totalBeneficio, totalDivida, meta.getValor(), meta.getValorEconomizar(), meta.getIsAvista(), 
					contazulR.findSaldoByNumeroContazul(meta.getNumeroContazul())), 
					util.calcularValorRestante(totalBeneficio, totalDivida, meta.getValor(), meta.getValorEconomizar()),
					meta);
			
			listaMeta.setPodeAplicar(util.verificarNaoPodeSAplicar(listaMeta.getStatus()));
			
			lista.add(listaMeta);
		}
		
		return lista;
	}
	
	@PostMapping("/aplicar")
	public void aplicar(long id) {
		
		// Select na tabela 7
		Ca07Meta ca07Meta = this.metaR.findById(id);
		// Veficando se é á vista
		if(ca07Meta.getIsAvista() == 1) {
			
			// Processo de atualização do saldo
			Ca02Contazul contazul = contazulR.findByNumeroContazul(ca07Meta.getNumeroContazul());
			contazul.setSaldo(contazul.getSaldo() - ca07Meta.getValor());
			contazulR.save(contazul);
		} else {
			
			// Transformo a meta em uma dívida
			dividaMensalR.save(new Ca06DividaMensal(ca07Meta.getDescricao(), ca07Meta.getValor(), ContasAPagar.PRIORIDADE_01, 0,
					ca07Meta.getQuantidadeParcela(), 0, null, ca07Meta.getNumeroContazul()));
		}
		
		// Definindo como atingida
		ca07Meta.setIsRealizada(1);
		
		// Grava os dados atualizados da tabela 7
		metaR.save(ca07Meta);
	}
	
	@PostMapping("/excluir")
	public void excluir(long id) {
		
		this.metaR.delete(this.metaR.findById(id));
	}
}




