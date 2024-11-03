package com.mycompany.aluguelveiculos.Controller;

import com.mycompany.aluguelveiculos.Model.Carro;
import com.mycompany.aluguelveiculos.Model.ListaServico;
import com.mycompany.aluguelveiculos.Model.Moto;
import com.mycompany.aluguelveiculos.Model.Servico;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ServicoController {

    public boolean cadastrar(String tipoVeiculo, int id, int cnh, String cliente, String modeloVeiculo, String dataRetirada,
            String dataDevolucao, boolean seguro, boolean itemDeSeguranca,
            boolean itemDeArmazenamento, int qtdPassageiro, boolean reboque) {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        Date dataRetiradaDate;
        Date dataDevolucaoDate;

        try {
            dataRetiradaDate = formato.parse(dataRetirada);
            dataDevolucaoDate = formato.parse(dataDevolucao);
        } catch (ParseException e) {
            System.err.println("Erro ao converter as datas: " + e.getMessage());
            return false;
        }

        Servico veiculo;

        switch (tipoVeiculo) {
            case "Carro":
                veiculo = new Carro(qtdPassageiro, reboque, id, cnh, cliente, modeloVeiculo,
                        dataRetiradaDate, dataDevolucaoDate, seguro);
                break;
            case "Moto":
                veiculo = new Moto(itemDeSeguranca, itemDeArmazenamento, id, cnh, cliente,
                        modeloVeiculo, dataRetiradaDate, dataDevolucaoDate, seguro);
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
            double valorAluguel = servico.calculoAluguel();

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

    public void preencherTabela(JTable jTabela) {
        DefaultTableModel dtm = (DefaultTableModel) jTabela.getModel();

        dtm.setRowCount(ListaServico.getInstance().size());
        jTabela.setModel(dtm);

        int posicaoLinha = 0;

        for (int i = 0; i < ListaServico.getInstance().size(); i++) {
            Servico servico = ListaServico.getInstance().get(i);

            jTabela.setValueAt(servico.getId(), posicaoLinha, 0);
            jTabela.setValueAt(servico.getCnh(), posicaoLinha, 1);
            jTabela.setValueAt(servico.getCliente(), posicaoLinha, 2);
            jTabela.setValueAt(servico.getModeloVeiculo(), posicaoLinha, 3);
            jTabela.setValueAt(servico.getDataRetirada(), posicaoLinha, 4);
            jTabela.setValueAt(servico.getDataDevolucao(), posicaoLinha, 5);
            jTabela.setValueAt(servico.isSeguro(), posicaoLinha, 6);

            if (servico instanceof Carro) {
                Carro carro = (Carro) servico;
                jTabela.setValueAt(carro.getQtdPassageiro(), posicaoLinha, 7);
                jTabela.setValueAt(carro.isReboque(), posicaoLinha, 8);
                jTabela.setValueAt("Não se aplica", posicaoLinha, 9); // Campo específico para Moto
                jTabela.setValueAt("Não se aplica", posicaoLinha, 10); // Campo específico para Moto
                jTabela.setValueAt(carro.calculoAluguel(), posicaoLinha, 11);
            } else if (servico instanceof Moto) {
                Moto moto = (Moto) servico;
                jTabela.setValueAt("Não se aplica", posicaoLinha, 7); // Campo específico para Carro
                jTabela.setValueAt("Não se aplica", posicaoLinha, 8); // Campo específico para Carro
                jTabela.setValueAt(moto.isItemDeSeguranca(), posicaoLinha, 9);
                jTabela.setValueAt(moto.isItemDeSeguranca(), posicaoLinha, 10);
                jTabela.setValueAt(moto.calculoAluguel(), posicaoLinha, 11);
            }
            
            posicaoLinha += 1;
        }
    }

    public void excluir(JTable jTabela) {

        if (jTabela.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um item na tabela!", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            int resposta = JOptionPane.showConfirmDialog(
                    null,
                    "Tem certeza que deseja excluir o item?",
                    "Aviso",
                    JOptionPane.YES_NO_OPTION
            );

            if (resposta == JOptionPane.YES_OPTION) {
                ListaServico.getInstance().remove(jTabela.getSelectedRow());
                JOptionPane.showMessageDialog(null, "Item excluído com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);

                preencherTabela(jTabela);
            }
        }
    }

    public void editar(int id, int cnh, String cliente, String modeloVeiculo, Date dataRetirada,
            Date dataDevolucao, boolean seguro, double valorAluguel, boolean itemDeSeguranca,
            boolean itemDeArmazenamento, int qtdPassageiro, boolean reboque) {

        int resposta = JOptionPane.showConfirmDialog(
                null,
                "Tem certeza que deseja atualizar o veículo?",
                "Aviso",
                JOptionPane.YES_NO_OPTION
        );

        if (resposta == JOptionPane.YES_OPTION) {

            int posicao = pesquisarVeiculo(id);

            Servico servico = ListaServico.getInstance().get(posicao);

            servico.setCnh(cnh);
            servico.setCliente(cliente);
            servico.setModeloVeiculo(modeloVeiculo);
            servico.setDataRetirada(dataRetirada);
            servico.setDataDevolucao(dataDevolucao);
            servico.setSeguro(seguro);

            if (servico instanceof Carro) {
                Carro carro = (Carro) servico;
                carro.setQtdPassageiro(qtdPassageiro);
                carro.setReboque(reboque);
            } else if (servico instanceof Moto) {
                Moto moto = (Moto) servico;
                moto.setItemDeSeguranca(itemDeSeguranca);
                moto.setItemDeArmazenamento(itemDeArmazenamento);
            }

            ListaServico.getInstance().set(posicao, servico);

            JOptionPane.showMessageDialog(null, "Veículo atualizado com sucesso", "Aviso", 1);
        }
    }

    private int pesquisarVeiculo(int id) {

        int posicao = -1;

        for (int i = 0; i < ListaServico.getInstance().size(); i++) {
            if (ListaServico.getInstance().get(i).getId() == id) {
                posicao = i;
            }
        }
        return posicao;
    }
}
