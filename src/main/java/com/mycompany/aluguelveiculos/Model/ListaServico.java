package com.mycompany.aluguelveiculos.Model;
import java.util.ArrayList;
import java.util.List;

public class ListaServico {
    
     private static List<Servico> lista;
    
    private ListaServico() {
        
    }
    
     public static List<Servico> getInstance(){
        if(lista == null){
            lista = new ArrayList<>();
        } 
        return lista;
    }
}