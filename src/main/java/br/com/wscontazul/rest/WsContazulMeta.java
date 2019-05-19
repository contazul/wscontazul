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
import br.com.wscontazul.statics.Meta;
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
		
		List<ListaMeta> lista = new ArrayList<>();
		List<Ca07Meta> metas = metaR.findByNumeroContazulAndIsRealizada(numeroContazul, 0);
		
		if(metas != null && metas.size() != 0) {
			
			double totalDivida = 0;
			double totalBeneficio = 0;
			
			if(this.dividaMensalR.quantidadeRegistro(numeroContazul) != 0)
				totalDivida = dividaMensalR.selectTotalDividaMensal(numeroContazul);
			
			if(this.lucroMensalR.quantidadeRegistro(numeroContazul) != 0)
				totalBeneficio = lucroMensalR.selectTotalLucroMensal(numeroContazul);
			
			for(Ca07Meta meta : metas) {
				
				UtilMeta util = new UtilMeta();
				
				String status = util.calcularStatus(totalBeneficio, totalDivida, meta.getValor(), 
						meta.getValorEconomizar(), meta.getIsAvista(),contazulR.findSaldoByNumeroContazul(meta.getNumeroContazul()));
				
				double valorRestante = 0;
				
				if(!status.equals(Meta.STATUS_02))
					valorRestante = util.calcularValorRestante(totalBeneficio, totalDivida, meta.getValor(), meta.getValorEconomizar()); 
				
				ListaMeta listaMeta = new ListaMeta(status, valorRestante,meta);
				
				listaMeta.setPodeAplicar(util.verificarNaoPodeAplicar(listaMeta.getStatus()));
				
				lista.add(listaMeta);
			}
		
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
	
	@GetMapping("/listaMetaStatus01")
	public List<ListaMeta> listaMetaStatus01(long numeroContazul) {
		
		List<ListaMeta> lista = this.listaMeta(numeroContazul);
		List<ListaMeta> listaNova = new ArrayList<>();
		for(ListaMeta item : lista) {
			
			if(item.getStatus().equals(Meta.STATUS_01))
				listaNova.add(item);
		}
		return listaNova;
	}
}





