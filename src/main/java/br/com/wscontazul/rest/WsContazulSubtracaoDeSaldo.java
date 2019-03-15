package br.com.wscontazul.rest;

import br.com.wscontazul.model.Ca02Contazul;
import br.com.wscontazul.model.Ca04SubtracaoSaldo;
import br.com.wscontazul.repository.Ca02ContazulRepository;
import br.com.wscontazul.repository.Ca04SubtracaoSaldoRepository;
import br.com.wscontazul.util.UtilDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/wscontazul/subtracaoDeSaldo")
public class WsContazulSubtracaoDeSaldo {

    @Autowired
    private Ca04SubtracaoSaldoRepository subtracaoSaldoR;

    @Autowired
    private Ca02ContazulRepository contazulR;

    @PostMapping("/inserirSubtracaoSaldo")
    public void inserirSubtracaoSaldo(long numeroContazul, double valor, String descricao, String prioridade) {

        subtracaoSaldoR.save(new Ca04SubtracaoSaldo(descricao, valor, prioridade, numeroContazul));
        Ca02Contazul ca02Contazul = contazulR.findByNumeroContazul(numeroContazul);
        ca02Contazul.setSaldo(ca02Contazul.getSaldo() - valor);
        contazulR.save(ca02Contazul);
    }

    @GetMapping("/listaDeSubtracaoSaldo")
    public List<Ca04SubtracaoSaldo> listaDeSubtracaoSaldo(long numeroContazul) {

        UtilDatas utilDatas = new UtilDatas();
        Date[] intervalo = utilDatas.getDateIntervaloDeDatasMensal();
        return subtracaoSaldoR.findByNumeroContazulAndDataMovimentoBetweenOrderByValorDesc(numeroContazul,
                intervalo[0], intervalo[1]);
    }
}
