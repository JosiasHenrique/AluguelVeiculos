package com.mycompany.aluguelveiculos.Model;

import java.util.Date;

public class Moto extends Servico {
    
    private boolean itemDeSeguranca, itemDeArmazenamento;

    public Moto(boolean itemDeSeguranca, boolean itemDeArmazenamento, int id, int cnh, String cliente,
            String modeloVeiculo, Date dataRetirada, Date dataDevolucao, boolean seguro, double valorAluguel) {
        super(id, cnh, cliente, modeloVeiculo, dataRetirada, dataDevolucao, seguro, valorAluguel);
        this.itemDeSeguranca = itemDeSeguranca;
        this.itemDeArmazenamento = itemDeArmazenamento;
    }

    public boolean isItemDeSeguranca() {
        return itemDeSeguranca;
    }

    public void setItemDeSeguranca(boolean itemDeSeguranca) {
        this.itemDeSeguranca = itemDeSeguranca;
    }

    public boolean isItemDeArmazenamento() {
        return itemDeArmazenamento;
    }

    public void setItemDeArmazenamento(boolean itemDeArmazenamento) {
        this.itemDeArmazenamento = itemDeArmazenamento;
    }
    
}

