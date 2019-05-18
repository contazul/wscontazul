package br.com.wscontazul.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wscontazul.model.Ca02Contazul;
import br.com.wscontazul.model.Ca06DividaMensal;
import br.com.wscontazul.model.Ca08Centralizacao;
import br.com.wscontazul.model.presenter.ListaContasAPagar;
import br.com.wscontazul.repository.Ca02ContazulRepository;
import br.com.wscontazul.repository.Ca06DividaMensalRepository;
import br.com.wscontazul.repository.Ca08CentralizacaoRepository;
import br.com.wscontazul.statics.Contazul;
import br.com.wscontazul.util.UtilDatas;

@RestController
@RequestMapping("/wscontazul/dividamensal")
public class WsContazulContasAPagar {
	
	
	@Autowired
	private Ca06DividaMensalRepository dividaMensalR;
	
	@Autowired
	private Ca02ContazulRepository contazulR;
	
	@Autowired
	private Ca08CentralizacaoRepository centralizacaoR;
	
	@PostMapping("/incluirconta")
	public void incluirConta(String descricao, double valor, String prioridade,
			int quantidadeParcela, long numeroContazul) {
		
		dividaMensalR.save(new Ca06DividaMensal(descricao, valor, prioridade, 0,
				quantidadeParcela, 0, null, numeroContazul));
	}
	
	@GetMapping("/listaDeDividaMensal")
	public List<ListaContasAPagar> listaDeDividaMensal(long numeroContazul) {
		
		Ca02Contazul contazul = contazulR.findByNumeroContazul(numeroContazul);

		if (contazul.getPerfil().equals(Contazul.TIPO2)) {
			
			List<ListaContasAPagar> dividas = new ArrayList<>();
			long[] centralizadas = this.centralizacaoR.findNumeroContazulCentralizadaByNumeroContazulCentralizadora(numeroContazul);
			for(int i = 0; i < centralizadas.length; i++) {
				
				List<Ca06DividaMensal> dividasCentralizada = dividaMensalR.findByNumeroContazulAndPago(centralizadas[i], 0);
				for(Ca06DividaMensal divida : dividasCentralizada) {
					
					UtilDatas utilDatas = new UtilDatas();
					dividas.add(new ListaContasAPagar(divida, utilDatas.converterSqlDateParaString(divida.getDataPagamento())));
				}
			}
			
			return dividas;
		}

		
		List<Ca06DividaMensal> dividas = dividaMensalR.findByNumeroContazulAndPago(numeroContazul, 0);
		List<ListaContasAPagar> contas = new ArrayList<>();
		for(Ca06DividaMensal divida : dividas) {
			
			UtilDatas utilDatas = new UtilDatas();
			contas.add(new ListaContasAPagar(divida, utilDatas.converterSqlDateParaString(divida.getDataPagamento())));
		}
        return contas;
	}
	
	@PostMapping("/quitarDivida")
	public void quitarDivida(long id_divida_mensal) {
		
		Ca06DividaMensal ca06DividaMensal =  dividaMensalR.findById(id_divida_mensal);
		int totalParcela = ca06DividaMensal.getQuantidadeParcela();
		int quantidadePaga = ca06DividaMensal.getQuantidadePaga();
		double valorDivida = ca06DividaMensal.getValor();
		double valorQuitar = (totalParcela - quantidadePaga) * valorDivida;
		Ca02Contazul ca02Contazul =  contazulR.findByNumeroContazul(ca06DividaMensal.getNumeroContazul());
		
		if(ca02Contazul.getPerfil().equals(Contazul.TIPO3)) {
			
			Ca08Centralizacao centralizacao = this.centralizacaoR.findIdByNumeroContazulCentralizada(ca02Contazul.getNumeroContazul());
			Ca02Contazul centralizadora = this.contazulR.findByNumeroContazul(centralizacao.getNumeroContazulCentralizadora());
			centralizadora.setSaldo(centralizadora.getSaldo() - valorQuitar);
			this.contazulR.save(centralizadora);
		}
		
		ca02Contazul.setSaldo(ca02Contazul.getSaldo() - valorQuitar);
		contazulR.save(ca02Contazul);
		ca06DividaMensal.setPago(1);
		UtilDatas utilDatas = new UtilDatas();
		ca06DividaMensal.setDataPagamento(utilDatas.getDataCorrente());
		dividaMensalR.save(ca06DividaMensal);
	}
	
	@PostMapping("/pagarDivida")
	public void pagarDIvida(long id_divida_mensal) {
		
		Ca06DividaMensal ca06DividaMensal = dividaMensalR.findById(id_divida_mensal);
		Ca02Contazul ca02Contazul =  contazulR.findByNumeroContazul(ca06DividaMensal.getNumeroContazul());
		
		if(ca02Contazul.getPerfil().equals(Contazul.TIPO3)) {
			
			Ca08Centralizacao centralizacao = this.centralizacaoR.findIdByNumeroContazulCentralizada(ca02Contazul.getNumeroContazul());
			Ca02Contazul centralizadora = this.contazulR.findByNumeroContazul(centralizacao.getNumeroContazulCentralizadora());
			centralizadora.setSaldo(centralizadora.getSaldo() - ca06DividaMensal.getValor());
			this.contazulR.save(centralizadora);
		}
		
		ca02Contazul.setSaldo(ca02Contazul.getSaldo() - ca06DividaMensal.getValor());
		contazulR.save(ca02Contazul);
		if(ca06DividaMensal.getQuantidadeParcela() != 0) {
			
			ca06DividaMensal.setQuantidadePaga(ca06DividaMensal.getQuantidadePaga() + 1);
			if(ca06DividaMensal.getQuantidadePaga() == ca06DividaMensal.getQuantidadeParcela())
				ca06DividaMensal.setPago(1);
		}
		UtilDatas utilDatas = new UtilDatas();
		ca06DividaMensal.setDataPagamento(utilDatas.getDataCorrente()); 
		dividaMensalR.save(ca06DividaMensal);
	}
	
	@DeleteMapping("/excluirDivida")
	public void excluirDivida(long id_divida_mensal) {
		
		Ca06DividaMensal ca06DividaMensal = dividaMensalR.findById(id_divida_mensal);
		dividaMensalR.delete(ca06DividaMensal);
	}
	
	@GetMapping("/listarDividaFixa")
	public List<Ca06DividaMensal> listarDividaFixa(long numeroContazul) {
		
		return dividaMensalR.findByNumeroContazulAndPagoAndQuantidadeParcela(numeroContazul, 0, 0);
	}
}
















