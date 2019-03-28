package br.com.wscontazul.rest;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wscontazul.model.Ca06DividaMensal;
import br.com.wscontazul.repository.Ca02ContazulRepository;
import br.com.wscontazul.repository.Ca06DividaMensalRepository;
import br.com.wscontazul.util.UtilDatas;

@RestController
@RequestMapping("/wscontazul/dividamensal")
public class WsContazulContasAPagar {
	
	
	@Autowired
	private Ca06DividaMensalRepository dividaMensalR;
	
	private Ca02ContazulRepository contazulR;
	
	@PostMapping("/incluirconta")
	public void incluirConta(String descricao, double valor, String prioridade,
			int quantidadeParcela, Date dataPagamento, long numeroContazul) {
		
		UtilDatas utilDatas = new UtilDatas();
		dividaMensalR.save(new Ca06DividaMensal(descricao, valor, prioridade, 0,
				quantidadeParcela, 0, utilDatas.getDataCorrente()));
	}
}
