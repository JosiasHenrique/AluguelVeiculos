package com.mycompany.aluguelveiculos.Model;

import java.util.Date;

public class Carro extends Servico {

    public Carro(int qtdPassageiro, boolean reboque, int id, int cnh, String cliente, String modeloVeiculo, Date dataRetirada, Date dataDevolucao, boolean seguro, double valorAluguel) {
        super(id, cnh, cliente, modeloVeiculo, dataRetirada, dataDevolucao, seguro, valorAluguel);
        this.qtdPassageiro = qtdPassageiro;
        this.reboque = reboque;
    }
    
    private int qtdPassageiro;
    private boolean reboque;

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
    public void calculoAluguel() {
        System.out.println("Ola");
    }
    
    
}
