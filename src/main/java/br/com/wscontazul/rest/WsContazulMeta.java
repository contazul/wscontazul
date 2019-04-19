package br.com.wscontazul.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wscontazul.model.Ca02Contazul;
import br.com.wscontazul.model.Ca07Meta;
import br.com.wscontazul.model.presenter.ListaMeta;
import br.com.wscontazul.repository.Ca02ContazulRepository;
import br.com.wscontazul.repository.Ca05LucroMensalRepository;
import br.com.wscontazul.repository.Ca06DividaMensalRepository;
import br.com.wscontazul.repository.Ca07MetaRepository;
import br.com.wscontazul.util.UtilDatas;
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
		ca07Meta.setQuantidadePaga(0);
		metaR.save(ca07Meta);
	}
	
	@GetMapping("/listaMeta")
	public List<ListaMeta> listaMeta(long numeroContazul) {
		
		double totalDivida = dividaMensalR.selectTotalDividaMensal(numeroContazul);
		double totalBeneficio = lucroMensalR.selectTotalLucroMensal(numeroContazul);
		
		List<Ca07Meta> metas = metaR.findByNumeroContazulAndIsRealizada(numeroContazul, 0);
		
		List<ListaMeta> lista = new ArrayList<>();
		
		for(Ca07Meta meta : metas) {
			
			UtilDatas utilData = new UtilDatas();
			UtilMeta util = new UtilMeta();
			lista.add(new ListaMeta(util.calcularValorRestante(totalBeneficio, totalDivida, meta.getValor(), meta.getValorEconomizar()),
					util.calcularStatus(totalBeneficio, totalDivida, meta.getValor(), meta.getValorEconomizar(), meta.getIsAvista()),
					util.verificarPagamentoMesCorrente(utilData.converterSqlDateParaString(meta.getDataAplicacao())),meta));
		}
		
		return lista;
	}
	
	@PostMapping("/aplicar")
	public void aplicar(long id) {
		
		// Select na tabela 7
		Ca07Meta ca07Meta = this.metaR.findById(id);
		// Atualizando a quantidade de parcelas pagas
		ca07Meta.setQuantidadePaga(ca07Meta.getQuantidadePaga() + 1);
		// Verificando se á meta foi atingida
		if(ca07Meta.getIsAvista() == 1 || 
				(ca07Meta.getQuantidadeParcela() != 0 && ca07Meta.getQuantidadePaga() == ca07Meta.getQuantidadeParcela()))
			ca07Meta.setIsRealizada(1);
		// Atualiza data de aplicação
		UtilDatas utilData = new UtilDatas();
		ca07Meta.setDataAplicacao(utilData.getDataCorrente());
		// Seta o pagamento referente ao mês
		Ca02Contazul ca02Contazul = contazulR.findByNumeroContazul(ca07Meta.getNumeroContazul());
		// Atualiza saldo
		ca02Contazul.setSaldo(ca02Contazul.getSaldo() - ca07Meta.getValor());
		// Grava os dados atualizados da tabela 2 e 7
		contazulR.save(ca02Contazul);
		metaR.save(ca07Meta);
	}
	
	@PostMapping("/excluir")
	public void excluir(long id) {
		
		this.metaR.delete(this.metaR.findById(id));
	}
}





