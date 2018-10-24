/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * 
 * @author Gabriel Strack
 */
public class MesEmpresa {
    @DBRef
    private Empresa empresa;
    @DBRef
    private Meses mes;
    private int numeroNotas;
    private Double valorTotal;
    private Double credito;

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Meses getMes() {
        return mes;
    }

    public void setMes(Meses mes) {
        this.mes = mes;
    }

    public int getNumeroNotas() {
        return numeroNotas;
    }

    public void setNumeroNotas(int numeroNotas) {
        this.numeroNotas = numeroNotas;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getCredito() {
        return credito;
    }

    public void setCredito(Double credito) {
        this.credito = credito;
    }
    
    public Double getMediaPorNota(){
        Double media;
        return media = getCredito() / getNumeroNotas();
    }
    
//    public Double getPercentual(){
//        Double percentual;
//        return percentual = 
//    }
}
