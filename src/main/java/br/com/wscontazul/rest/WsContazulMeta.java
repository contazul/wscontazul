package br.com.wscontazul.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wscontazul.model.Ca07Meta;
import br.com.wscontazul.model.presenter.ListaMeta;
import br.com.wscontazul.repository.Ca05LucroMensalRepository;
import br.com.wscontazul.repository.Ca06DividaMensalRepository;
import br.com.wscontazul.repository.Ca07MetaRepository;
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
	
	@PostMapping("/incluirMeta")
	public void incluirMeta(String descricao, double valor, int isAvista, double valorEconomizar, 
			int quantidadeParcela, long numeroContazul) {
		
		metaR.save(new Ca07Meta(descricao, valor, isAvista, valorEconomizar, numeroContazul, quantidadeParcela));
	}
	
	@GetMapping("/listaMeta")
	public List<ListaMeta> listaMeta(long numeroContazul) {
		
		double totalDivida = dividaMensalR.selectTotalDividaMensal(numeroContazul);
		double totalBeneficio = lucroMensalR.selectTotalLucroMensal(numeroContazul);
		
		List<Ca07Meta> metas = metaR.findByNumeroContazul(numeroContazul);
		
		List<ListaMeta> lista = new ArrayList<>();
		
		for(Ca07Meta meta : metas) {
			
			UtilMeta util = new UtilMeta();
			lista.add(new ListaMeta(util.calcularValorRestante(totalBeneficio, totalDivida, meta.getValor(), meta.getValorEconomizar()),
					util.calcularStatus(totalBeneficio, totalDivida, meta.getValor(), meta.getValorEconomizar(), meta.getIsAvista())
					,meta));
		}
		
		return lista;
	}
}





