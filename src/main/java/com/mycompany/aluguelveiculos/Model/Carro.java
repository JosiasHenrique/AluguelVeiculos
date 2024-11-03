package com.mycompany.aluguelveiculos.Model;

import java.util.Date;

public class Carro extends Servico {

    private int qtdPassageiro;
    private boolean reboque;

    public Carro(int qtdPassageiro, boolean reboque, int id, int cnh, String cliente, String modeloVeiculo, Date dataRetirada, Date dataDevolucao, boolean seguro) {
        super(id, cnh, cliente, modeloVeiculo, dataRetirada, dataDevolucao, seguro);
        this.qtdPassageiro = qtdPassageiro;
        this.reboque = reboque;
    }

    public int getQtdPassageiro() {
        return qtdPassageiro;
    }

    public void setQtdPassageiro(int qtdPassageiro) {
        this.qtdPassageiro = qtdPassageiro;
    }

    public boolean isReboque() {
        return reboque;
    }

    public void setReboque(boolean reboque) {
        this.reboque = reboque;
    }

    @Override
    public double calculoAluguel() {
        double valorFinal = 0.0;
        long diasAluguel = calcularDias();

        switch (getModeloVeiculo()) {
            case "Sedan":
                valorFinal = 100.0;
                break;
            case "SUV":
                valorFinal = 150.0;
                break;
            case "Esportivo":
                valorFinal = 200.0;
                break;
            default:
                valorFinal = 100.0; // Valor padrão se o modelo não estiver listado
                break;
        }

        valorFinal *= diasAluguel;

        if (isSeguro()) {
            valorFinal += valorFinal * 0.2; // 20% a mais com seguro
        }

        if (qtdPassageiro > 4) {
            valorFinal += valorFinal * 0.1; // 10% a mais
        }

        if (reboque) {
            valorFinal += 50.0; // Valor fixo adicional para reboque
        }

        return valorFinal;
    }
    
}
