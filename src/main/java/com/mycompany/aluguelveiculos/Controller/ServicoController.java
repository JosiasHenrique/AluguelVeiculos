package com.mycompany.aluguelveiculos.Controller;

import com.mycompany.aluguelveiculos.Enum.TipoVeiculo;
import com.mycompany.aluguelveiculos.Model.Carro;
import com.mycompany.aluguelveiculos.Model.ListaServico;
import com.mycompany.aluguelveiculos.Model.Moto;
import com.mycompany.aluguelveiculos.Model.Servico;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

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

    public List<Object> pesquisar(int id) {

        boolean achou = false;
        int posicao = -1;

        for (int i = 0; i < ListaServico.getInstance().size(); i++) {

            if (ListaServico.getInstance().get(i).getId() == id) {
                achou = true;
                posicao = i;
            }
        }

        if (achou == true) {
            Servico servico = ListaServico.getInstance().get(posicao);

            int cnh = servico.getCnh();
            String cliente = servico.getCliente();
            String modeloVeiculo = servico.getModeloVeiculo();
            Date dataRetirada = servico.getDataRetirada();
            Date dataDevolucao = servico.getDataDevolucao();
            boolean seguro = servico.isSeguro();
            double valorAluguel = servico.getValorAluguel();

            List<Object> dados = new ArrayList<>(Arrays.asList(id, cnh, cliente, modeloVeiculo, dataRetirada,
                    dataDevolucao, seguro, valorAluguel));

            if (servico instanceof Carro) {
                Carro carro = (Carro) servico;
                dados.add(carro.getQtdPassageiro());
                dados.add(carro.isReboque());
            } else if (servico instanceof Moto) {
                Moto moto = (Moto) servico;
                dados.add(moto.isItemDeSeguranca());
                dados.add(moto.isItemDeArmazenamento());
            }

            return dados;
        } else {
            JOptionPane.showMessageDialog(null, "Veículo não encontrado!", "Aviso", 0);
            return null;
        }
    }
}
