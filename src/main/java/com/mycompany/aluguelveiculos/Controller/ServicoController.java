package com.mycompany.aluguelveiculos.Controller;

import com.mycompany.aluguelveiculos.Enum.TipoVeiculo;
import com.mycompany.aluguelveiculos.Model.Carro;
import com.mycompany.aluguelveiculos.Model.ListaServico;
import com.mycompany.aluguelveiculos.Model.Moto;
import com.mycompany.aluguelveiculos.Model.Servico;
import java.util.Date;

public class ServicoController {

    public boolean cadastrar(TipoVeiculo tipoVeiculo, int id, int cnh, String cliente, String modeloVeiculo, Date dataRetirada,
            Date dataDevolucao, boolean seguro, double valorAluguel, boolean itemDeSeguranca,
            boolean itemDeArmazenamento, int qtdPassageiro, boolean reboque) {

        Servico veiculo;

        switch (tipoVeiculo) {
            case CARRO:
                veiculo = new Carro(qtdPassageiro, reboque, id, cnh, cliente, modeloVeiculo,
                        dataRetirada, dataDevolucao, seguro, valorAluguel);
                break;
            case MOTO:
                veiculo = new Moto(itemDeSeguranca, itemDeArmazenamento, id, cnh, cliente,
                        modeloVeiculo, dataRetirada, dataDevolucao, seguro, valorAluguel);
                break;
            default:
                throw new IllegalArgumentException("Tipo de veículo inválido.");
        }

        return ListaServico.getInstance().add(veiculo);
    }
}
