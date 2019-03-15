package br.com.wscontazul.rest;

import br.com.wscontazul.model.Ca05LucroMensal;
import br.com.wscontazul.repository.Ca05LucroMensalRepository;
import br.com.wscontazul.util.UtilDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wscontazul/lucroMensal")
public class WsContazulLucroMensal {

    @Autowired
    private Ca05LucroMensalRepository lucroMensalR;

    @PostMapping("/inserirLucroMensal")
    public void inserirLucroMensal(long numeroContazul, String descricao, double valor,
                                   int diaRecebimento) {

        lucroMensalR.save(new Ca05LucroMensal(descricao, valor, diaRecebimento, numeroContazul));
    }

    @PostMapping("/excluirLucroMensal")
    public void excluirLucroMensal(long id) {

        lucroMensalR.delete(lucroMensalR.findById(id));
    }

    @GetMapping("/listaDeLucroMensal")
    public List<Ca05LucroMensal> listaDeLucroMensal(long numeroContazul) {

        return lucroMensalR.findByNumeroContazulOrderByValorDesc(numeroContazul);
    }
}
