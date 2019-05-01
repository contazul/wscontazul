package br.com.wscontazul.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wscontazul.model.Ca07Meta;
import br.com.wscontazul.model.presenter.Central;
import br.com.wscontazul.model.presenter.SimuladorEntrada;
import br.com.wscontazul.model.presenter.SimuladorSaida;
import br.com.wscontazul.repository.Ca02ContazulRepository;
import br.com.wscontazul.repository.Ca07MetaRepository;
import br.com.wscontazul.util.UtilContazul;
import br.com.wscontazul.util.UtilMeta;

@RestController
@RequestMapping("/wscontazul/simulador")
public class WsContazulSimulador {
	
	@Autowired
	private Ca02ContazulRepository contazulR;
	
	@Autowired
	private WsContazulCentral wscentral;
	
	@Autowired
	private Ca07MetaRepository metaR;
	
	@GetMapping("/simular")
	public SimuladorSaida simular(SimuladorEntrada se) {
		
		// CARREGANDO INFORMAÇÕES ATUAIS
		Central dadosAtuais = this.wscentral.informacoesCentral(se.getNumeroContazul());
		//
		
		// OBTENDO INFORMAÇÕES PARA OBTER O NOVO STATUS
		double valorIdeal = this.contazulR.findValorIdealByNumeroContazul(se.getNumeroContazul());
		
		double totalBeneficio = (dadosAtuais.getTotalBeneficioMensal() + se.getTotalBeneficio()) - se.getTotalBeneficioRemovido();
		
		double totalDivida = (dadosAtuais.getTotalDividaMensal() + se.getTotalDivida()) - se.getTotalBeneficioRemovido();
		//
		
		// OBTENDO STATUS
		UtilContazul utilC = new UtilContazul();
		String statusSimulado = utilC.gerarStatusContazul(valorIdeal, totalDivida, totalBeneficio);
		//
		
		// OBTENDO PERCENTUAL
		double percentualEconomizadoSimulado = utilC.calcularPercentualEconomizado(totalBeneficio, totalDivida);
		//
		
		// OBTENDO VALOR ECONOMIZADO ATUAL E SIMULADO
		double valorAtualEconomizado = utilC.calcularValorEconomizado(dadosAtuais.getTotalBeneficioMensal(), dadosAtuais.getTotalDividaMensal());
		double valorSimuladoEconomizado = utilC.calcularValorEconomizado(totalBeneficio, totalDivida);
		//
		
		SimuladorSaida ss = new SimuladorSaida();
		
		// CARREGANDO A INFORMAÇÃO QUE SERVE PARA VERIFICAR SE HOUVE LUCRO
		ss.setLucrou(valorSimuladoEconomizado > valorAtualEconomizado);
		//
		
		// CALCULANDO DIFERENÇA ENTRE A SIMULAÇÃO E OS DADOS REAIS
		if(ss.isLucrou())
			ss.setValorAfetado(valorSimuladoEconomizado - valorAtualEconomizado);
		else
			ss.setValorAfetado(valorAtualEconomizado - valorSimuladoEconomizado);
		//
		
		// CARREGANDO INFORMAÇÕES DE META
		if(se.isSimulandoMeta()) {
			
			Ca07Meta meta = metaR.findById(se.getIdMeta());
			UtilMeta utilMeta = new UtilMeta();
			ss.setStatusMeta(utilMeta.calcularStatus(totalBeneficio, totalDivida, meta.getValor(), meta.getValorEconomizar(), 
					meta.getIsAvista(), contazulR.findSaldoByNumeroContazul(se.getNumeroContazul())));
			ss.setValorRestanteMeta(utilMeta.calcularValorRestante(totalBeneficio, totalDivida, meta.getValor(), meta.getValorEconomizar()));
		}
		//
			
		// CARREGANDO OBJETO DE RETORNO
		ss.setStatusAtual(dadosAtuais.getStatus());
		ss.setStatusSimulado(statusSimulado);
		ss.setPercentualEconomizadoAtual(dadosAtuais.getPercentualEconomizado());
		ss.setPercentualEconomizadoSimulado(percentualEconomizadoSimulado);
		ss.setValorEconomizadoAtual(valorAtualEconomizado);
		ss.setValorEconomizadoSimulado(valorSimuladoEconomizado);
		//
		return ss;
	}
}












