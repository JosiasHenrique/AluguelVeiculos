package com.mycompany.aluguelveiculos.Model;

import java.util.Date;

public abstract class Servico {
    private int id, cnh;
    private String cliente, modeloVeiculo;
    private Date dataRetirada, dataDevolucao;
    private boolean seguro;
    private double valorAluguel;

    public Servico(int id, int cnh, String cliente, String modeloVeiculo,
            Date dataRetirada, Date dataDevolucao, boolean seguro,
            double valorAluguel) {
        this.id = id;
        this.cnh = cnh;
        this.cliente = cliente;
        this.modeloVeiculo = modeloVeiculo;
        this.dataRetirada = dataRetirada;
        this.dataDevolucao = dataDevolucao;
        this.seguro = seguro;
        this.valorAluguel = valorAluguel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCnh() {
        return cnh;
    }

    public void setCnh(int cnh) {
        this.cnh = cnh;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getModeloVeiculo() {
        return modeloVeiculo;
    }

    public void setModeloVeiculo(String modeloVeiculo) {
        this.modeloVeiculo = modeloVeiculo;
    }

    public Date getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(Date dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public boolean isSeguro() {
        return seguro;
    }

    public void setSeguro(boolean seguro) {
        this.seguro = seguro;
    }

    public double getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(double valorAluguel) {
        this.valorAluguel = valorAluguel;
    }
    
    public void calculoAluguel(){}

    @Override
    public String toString() {
        return "Servico{" + "id=" + id +
                ", cnh=" + cnh + ", cliente=" +
                cliente + ", modeloVeiculo=" +
                modeloVeiculo + ", dataRetirada=" +
                dataRetirada + ", dataDevolucao=" +
                dataDevolucao + ", seguro=" +
                seguro + ", valorAluguel=" +
                valorAluguel + '}';
    }
    
}