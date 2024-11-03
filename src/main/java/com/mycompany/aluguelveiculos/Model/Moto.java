package com.mycompany.aluguelveiculos.Model;

import java.util.Date;

public class Moto extends Servico {
    
    private boolean itemDeSeguranca, itemDeArmazenamento;

    public Moto(boolean itemDeSeguranca, boolean itemDeArmazenamento, int id, int cnh, String cliente,
            String modeloVeiculo, Date dataRetirada, Date dataDevolucao, boolean seguro, double valorAluguel) {
        super(id, cnh, cliente, modeloVeiculo, dataRetirada, dataDevolucao, seguro);
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
    
    @Override
    public double calculoAluguel() {
        double valorFinal = 0.0;
        long diasAluguel = calcularDias();

        switch (getModeloVeiculo()) {
            case "Scooter":
                valorFinal = 50.0;
                break;
            case "Naked":
                valorFinal = 70.0;
                break;
            case "Trail":
                valorFinal = 90.0;
                break;
            default:
                valorFinal = 50.0; // Valor padrão se o modelo não estiver listado
                break;
        }

        valorFinal *= diasAluguel;

        if (isSeguro()) {
            valorFinal += valorFinal * 0.15; // 15% a mais com seguro
        }

        if (itemDeSeguranca) {
            valorFinal += 20.0; // Valor fixo adicional para item de segurança
        }

        if (itemDeArmazenamento) {
            valorFinal += 15.0; // Valor fixo adicional para item de armazenamento
        }

        return valorFinal;
    }
    
}

